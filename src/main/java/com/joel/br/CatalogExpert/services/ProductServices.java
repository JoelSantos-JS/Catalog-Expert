package com.joel.br.CatalogExpert.services;

import com.joel.br.CatalogExpert.dto.CategoryDTO;
import com.joel.br.CatalogExpert.dto.ProductDTO;
import com.joel.br.CatalogExpert.mapper.CategoryMapper;
import com.joel.br.CatalogExpert.mapper.ProductMapper;
import com.joel.br.CatalogExpert.model.Category;
import com.joel.br.CatalogExpert.model.Product;
import com.joel.br.CatalogExpert.repository.CategoryRepository;
import com.joel.br.CatalogExpert.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServices {
    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;



    public Page<ProductDTO> findAll(Pageable page) {
        Page<Product> list = repository.findAll(page);
        return list.map(e -> mapper.productDTO(e));
    }

    public ProductDTO findById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return mapper.productDTO(product);
    }
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = new Product();
        CopyToEntity(productDTO , product);
     Product productSave =    repository.save(product);

        return   mapper.productDTO(productSave);
    }

    public ProductDTO updateProduct(Long id ,ProductDTO productDTO) {
        Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        BeanUtils.copyProperties(productDTO , product, "id");
        Product product1 = repository.save(product);

        return mapper.productDTO(product1);
    }



    public void  deleteById(Long id) {
        repository.deleteById(id);
    }
    private void CopyToEntity(ProductDTO productDTO , Product product) {
       product.setDescription(productDTO.getDescription());
       product.setPrice(productDTO.getPrice());
       product.setImgUrl(productDTO.getImgUrl());
       product.setName(productDTO.getName());
       product.getCategories().clear();


       for(CategoryDTO productDTO1 :  productDTO.getCategories()) {
           Category category = categoryRepository.getOne(productDTO1.getId());
           product.getCategories().add(category);
       }
    }
}
