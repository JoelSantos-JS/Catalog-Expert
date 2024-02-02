package com.joel.br.CatalogExpert.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joel.br.CatalogExpert.dto.ProductDTO;
import com.joel.br.CatalogExpert.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class ProductServicesTestIT {


    @Autowired
    private ProductServices productServices;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private ObjectMapper mapper;
    private Long  existingId;
    private Long nonExistingId;
    private Long countTotalProducts;

    @BeforeEach
    void setup() throws Exception {
        existingId = 1L;
        nonExistingId = 25L;
        countTotalProducts = 2L;
    }

    @Test
    public void  deleteShouldDeleteResourceWhenIdExists() {
        productServices.deleteById(existingId);

        assertEquals(countTotalProducts - 1, productRepository.findAll().stream().count() );
    }


    @Test
    public void findAllPageShouldReturnPage() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<ProductDTO> result = productServices.findAll(pageRequest);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(0, result.getNumber());
        Assertions.assertEquals(10, result.getSize());
        Assertions.assertEquals(countTotalProducts, result.getTotalElements());
    }


    @Test
    public void  shouldUpdateAndReturnOk() throws Exception {
        ProductDTO productDTO = new ProductDTO(1L,"JoEL" , "JOEL desc" , 2.0 , "XXXXXXXXXXXXXXXXXXXXXX");
            String jsonBody = mapper.writeValueAsString(productDTO);
        String expectedName = productDTO.getName();
        ResultActions result = mvc.perform(put("/api/v1/products/{id}", existingId)
                        .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").value(expectedName));
    }
}