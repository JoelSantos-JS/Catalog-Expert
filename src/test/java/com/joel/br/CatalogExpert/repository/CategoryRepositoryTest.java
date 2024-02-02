package com.joel.br.CatalogExpert.repository;

import com.joel.br.CatalogExpert.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Transactional
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository repository;



    @BeforeEach
    void beforeE() {
        Category category =  new Category(1L, "Test", Instant.now(), Instant.now());
        repository.save(category);
    }
    @Test
    void findALl() {

        Assertions.assertTrue(repository.findAll().size() > 0);
    }


    @Test
    void findById() {
    Assertions.assertTrue(repository.findById(1L).isPresent());
    Assertions.assertTrue(repository.findById(3L).isEmpty());
    }


    @Test
    void save() {
        Category category = new Category(2L, "Test2", Instant.now(), Instant.now());
        repository.save(category);
        Assertions.assertTrue(repository.findById(2L).isPresent());
    }


    @Test
    void  deleteById() {
        repository.deleteById(1l);
        assertTrue(repository.findById(1L).isEmpty());
        assertFalse(repository.findById(1L).isPresent());
        assertFalse(repository.findById(2L).isPresent());
        assertFalse(repository.findById(3L).isPresent());
    }
}