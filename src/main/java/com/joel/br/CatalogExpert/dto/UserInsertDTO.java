package com.joel.br.CatalogExpert.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserInsertDTO extends  UserDTO
{
    private String password;

    public UserInsertDTO()
    {
        super();
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
