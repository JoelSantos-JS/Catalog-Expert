package com.joel.br.CatalogExpert.dto;

import com.joel.br.CatalogExpert.model.Category;
import com.joel.br.CatalogExpert.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProductDTO {
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    private Set<CategoryDTO> categories = new HashSet<>();

    public ProductDTO(String name, String description, Double price, String imgUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;

    }

    public ProductDTO(Product entity) {
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
    }
    public ProductDTO (Product product , Set<Category> categories) {
        this(product);

        categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
    }
}
