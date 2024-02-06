package com.joel.br.CatalogExpert.mapper;

import com.joel.br.CatalogExpert.dto.CategoryDTO;
import com.joel.br.CatalogExpert.dto.UserDTO;
import com.joel.br.CatalogExpert.model.Category;
import com.joel.br.CatalogExpert.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;



    public User toDto (UserDTO dto) {
        return mapper.map(dto, User.class);
    }


    public UserDTO toEntity(User user) {
        return mapper.map(user, UserDTO.class);
    }
}
