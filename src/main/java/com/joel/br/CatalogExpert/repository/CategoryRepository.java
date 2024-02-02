package com.joel.br.CatalogExpert.repository;

import com.joel.br.CatalogExpert.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.file.LinkOption;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
