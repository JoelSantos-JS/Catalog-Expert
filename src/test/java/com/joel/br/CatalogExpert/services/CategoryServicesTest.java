package com.joel.br.CatalogExpert.services;

import com.joel.br.CatalogExpert.dto.CategoryDTO;
import com.joel.br.CatalogExpert.mapper.CategoryMapper;
import com.joel.br.CatalogExpert.model.Category;
import com.joel.br.CatalogExpert.model.Product;
import com.joel.br.CatalogExpert.repository.CategoryRepository;
import jakarta.annotation.security.RunAs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CategoryServicesTest {


    @Mock
    private CategoryRepository repository;


    @Mock
    private CategoryMapper mapper;

    @InjectMocks
    private CategoryServices service;

    private Long ExistentId;

    private PageImpl<Category> page;
    private  Category category;

    @BeforeEach
    void before() {
        ExistentId = 1L;
        category = new Category(1L, "Electronics");
        page = new PageImpl<>(List.of(category));

        Mockito.when(repository.findAll((Pageable) any())).thenReturn(page);
        Mockito.when(repository.save(any(Category.class))).thenReturn(category);
    }




    @Test
    void shouldShowResultWhenIdExists() {
        // Given
        long existentId = 1L;
        Category category = new Category(existentId, "Electronics");
        Mockito.when(repository.findById(existentId)).thenReturn(java.util.Optional.of(category));

        // When
        // Perform the action that should trigger the findById method

        // Then
        Mockito.verify(repository).findById(existentId);
        // Add assertions or further verifications based on your requirements
    }
    @Test
    void shouldDoNothingWhenIdExists(){
        Assertions.assertDoesNotThrow(() -> service.deleteCategory(ExistentId));
        Mockito.verify(repository, Mockito.times(1)).deleteById(ExistentId);
    }

    @Test
    void findAllPagedShouldReturnPage(){
        Pageable pageable = PageRequest.of(0,10);

        Page<CategoryDTO> result = service.findAll(pageable);

        Assertions.assertNotNull(result);
        Mockito.verify(repository).findAll(pageable);
    }
    @Test
    void shouldCreateACategory() {
        // Arrange
        var expectedName = "Electronics";
        var expectedId = 1L;
        Category dto = new Category(1L, "Electronics");


    }
}