package com.joel.br.CatalogExpert.repository;

import com.joel.br.CatalogExpert.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>
{
    @Query(nativeQuery = true, value = """
				SELECT users.email AS username, users.password, role.id AS roleId, role.authority
			 				FROM users
			 				INNER JOIN users_role ON users.id = users_role.users_id
			 				INNER JOIN role ON role.id = users_role.role_id
			 				WHERE users.email = :email
			""")
	List<UserDetailsProjection> searchUsersAndRoleByEmail(String email);


    UserDetails findByEmail(String email);
}
