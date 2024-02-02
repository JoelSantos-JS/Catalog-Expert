package com.joel.br.CatalogExpert.dto;

import com.joel.br.CatalogExpert.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;


    public CategoryDTO(Category cat) {
        this.name = cat.getName();
        this.createdAt = cat.getCreatedAt();
        this.updatedAt = cat.getUpdatedAt();
    }

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
