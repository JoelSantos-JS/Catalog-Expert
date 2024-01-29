package com.joel.br.CatalogExpert.controller;

import com.joel.br.CatalogExpert.dto.ProductDTO;
import com.joel.br.CatalogExpert.services.ProductServices;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductServices services;




    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable ){
        return ResponseEntity.ok(services.findAll(pageable));
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(services.findById(id));
    }
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto){
        return ResponseEntity.ok(services.createProduct(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id ,@RequestBody ProductDTO dto){
        return ResponseEntity.ok(services.updateProduct(id , dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        services.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
