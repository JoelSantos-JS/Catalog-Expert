package com.joel.br.CatalogExpert.controller;

import com.joel.br.CatalogExpert.dto.CategoryDTO;
import com.joel.br.CatalogExpert.services.CategoryServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/category")
@AllArgsConstructor
public class CategoryController {


    private final CategoryServices services;



    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        return ResponseEntity.ok().body(services.findAll());
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(services.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {

        return ResponseEntity.ok().body(services.createCategory(categoryDTO));
    }
}
