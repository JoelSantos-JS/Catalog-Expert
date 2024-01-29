package com.joel.br.CatalogExpert.mapper;

import com.joel.br.CatalogExpert.dto.ProductDTO;
import com.joel.br.CatalogExpert.model.Product;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductMapper {

    private final ModelMapper mapper;




    public ProductDTO productDTO(Product product) {
        return mapper.map(product, ProductDTO.class);
    }

    public Product product(ProductDTO dto) {
        return mapper.map(dto, Product.class);

    }
}
