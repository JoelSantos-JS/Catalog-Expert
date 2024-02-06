package com.joel.br.CatalogExpert.dto;

import com.joel.br.CatalogExpert.model.Role;
import lombok.Data;

@Data
public class RoleDTO {
    private Long id;
    private String authority;


    public RoleDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }
}