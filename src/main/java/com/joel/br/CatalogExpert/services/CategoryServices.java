package com.joel.br.CatalogExpert.services;

import com.joel.br.CatalogExpert.dto.CategoryDTO;
import com.joel.br.CatalogExpert.exceptions.UserNotFoundException;
import com.joel.br.CatalogExpert.mapper.CategoryMapper;
import com.joel.br.CatalogExpert.model.Category;
import com.joel.br.CatalogExpert.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServices {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;





    public Page<CategoryDTO> findAll(Pageable pageable) {
        Page<Category> category = categoryRepository.findAll(pageable);
        return category.map(e -> mapper.toEntity(e));
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


    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category category  = categoryRepository.findById(id).get();

        if(category != null) {
            BeanUtils.copyProperties(dto, category, "id");
            categoryRepository.save(category);
            return mapper.toEntity(category);
        }else  {
            return  null;
        }


    }


    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
