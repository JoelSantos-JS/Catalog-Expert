package com.joel.br.CatalogExpert.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.joel.br.CatalogExpert.dto.CategoryDTO;
import com.joel.br.CatalogExpert.services.CategoryServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryServices services;
    @Autowired
    private ObjectMapper mapper;
    private Long ExistingId;
    private Long NonExistingId;
    private PageImpl<CategoryDTO > page;
    private CategoryDTO categoryDTO;


    @BeforeEach
    void setup() throws  Exception {
        ExistingId = 1L;
        NonExistingId = 1000L;
        categoryDTO = new CategoryDTO(1L, "name");
        page = new PageImpl<>(List.of(categoryDTO));

        System.out.println(categoryDTO);
        when(services.findAll(any())).thenReturn(page);
        when(services.findById(ExistingId)).thenReturn(categoryDTO);
        when(services.createCategory(any())).thenReturn(categoryDTO);
        when(services.update(eq(ExistingId), any())).thenReturn(categoryDTO);

        doNothing().when(services).deleteCategory(ExistingId);
    }

    @Test
    public  void findAllShouldReturnPage() throws Exception {
      ResultActions resultActions =  mockMvc.perform(get("/api/v1/category")
              .accept(MediaType.APPLICATION_JSON));
                resultActions.andExpect(status().isOk());
    }


    @Test
    public void  shouldReturnTheCategoryInIdExists() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/v1/category/{id}", ExistingId)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
    }


    @Test
    public void  shouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/v1/category/{id}", NonExistingId)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }


    @Test
    public void  shouldUpdateTheCategoryInIdExists() throws Exception {

        String json = mapper.writeValueAsString(categoryDTO);

        ResultActions result = mockMvc.perform(put("/api/v1/category/{id}", ExistingId).content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").value("name"));
        result.andExpect(jsonPath("$.id").value(1L));

    }

    @Test
    public void  shouldReturnCreatedWhenCategoryIsValid() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/v1/category").content(mapper.writeValueAsString(categoryDTO)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").value("name"));
        result.andExpect(jsonPath("$.id").value(1L));
    }

    @Test

    public void  shouldDeleteTheCategoryInIdExists() throws Exception {
        ResultActions result = mockMvc.perform(delete("/api/v1/category/{id}", ExistingId)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNoContent());
        result.andExpect(jsonPath("$.id").doesNotExist());



    }

}