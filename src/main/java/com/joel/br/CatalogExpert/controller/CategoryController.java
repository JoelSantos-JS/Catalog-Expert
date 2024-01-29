package com.joel.br.CatalogExpert.controller;

import com.joel.br.CatalogExpert.dto.CategoryDTO;
import com.joel.br.CatalogExpert.services.CategoryServices;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/category")
@AllArgsConstructor
public class CategoryController {


    private final CategoryServices services;



    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(services.findAll(pageable));
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(services.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(services.createCategory(categoryDTO));
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok().body(services.update(id,categoryDTO));
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCategoru(@PathVariable Long id) {
        services.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
