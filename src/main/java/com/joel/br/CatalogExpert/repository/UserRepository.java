package com.joel.br.CatalogExpert.repository;

import com.joel.br.CatalogExpert.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>
{
}
