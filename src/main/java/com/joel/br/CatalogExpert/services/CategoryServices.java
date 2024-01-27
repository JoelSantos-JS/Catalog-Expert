package com.joel.br.CatalogExpert.services;

import com.joel.br.CatalogExpert.dto.CategoryDTO;
import com.joel.br.CatalogExpert.exceptions.UserNotFoundException;
import com.joel.br.CatalogExpert.mapper.CategoryMapper;
import com.joel.br.CatalogExpert.model.Category;
import com.joel.br.CatalogExpert.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServices {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;





    public List<CategoryDTO> findAll() {
        List<Category> category = categoryRepository.findAll();
        return category.stream().map(e -> mapper.toEntity(e)).collect(Collectors.toList());
    }


    public CategoryDTO findById(Long id) {
        Category category2 = categoryRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapper.toEntity(category2);
    }





    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());

        var entitySaved = categoryRepository.save(category);


        CategoryDTO categoryDTO1  = mapper.toEntity(entitySaved);
        return categoryDTO1;

    }
}
