package com.joel.br.CatalogExpert.repository;

import com.joel.br.CatalogExpert.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
