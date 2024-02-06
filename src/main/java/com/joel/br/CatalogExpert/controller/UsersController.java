package com.joel.br.CatalogExpert.controller;

import com.joel.br.CatalogExpert.dto.UserDTO;
import com.joel.br.CatalogExpert.dto.UserInsertDTO;
import com.joel.br.CatalogExpert.services.UserServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/users")
@AllArgsConstructor
public class UsersController {

    private final UserServices usersService;




    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable){

        return ResponseEntity.ok().body(usersService.findAllPaged(pageable));
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(usersService.findById(id));
    }


    @PostMapping
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserInsertDTO dto){
        return ResponseEntity.ok().body(usersService.save(dto));
    }


    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){

        usersService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
