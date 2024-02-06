package com.joel.br.CatalogExpert.dto;

import com.joel.br.CatalogExpert.model.Role;
import com.joel.br.CatalogExpert.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
public class UserDTO {
    private  Long id;
    private String firstName;

    private String lastName;
    private String email;
    private String password;
    private Set<RoleDTO> roles = new HashSet<>();


    public UserDTO(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }


    public UserDTO(User user , Set<Role> roles) {
        this(user);
        roles.forEach(role -> this.roles.add(new RoleDTO(role)));
    }
}
