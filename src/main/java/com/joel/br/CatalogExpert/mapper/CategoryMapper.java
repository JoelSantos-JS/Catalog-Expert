package com.joel.br.CatalogExpert.mapper;

import com.joel.br.CatalogExpert.dto.CategoryDTO;
import com.joel.br.CatalogExpert.model.Category;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryMapper {

    private final ModelMapper mapper;



    public Category toDto (CategoryDTO dto) {
        return mapper.map(dto, Category.class);
    }


    public CategoryDTO toEntity(Category category) {
        return mapper.map(category, CategoryDTO.class);
    }
}
