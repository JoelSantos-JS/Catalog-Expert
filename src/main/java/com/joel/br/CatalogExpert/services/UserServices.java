package com.joel.br.CatalogExpert.services;

import com.joel.br.CatalogExpert.dto.*;
import com.joel.br.CatalogExpert.exceptions.UserNotFoundException;
import com.joel.br.CatalogExpert.mapper.UserMapper;
import com.joel.br.CatalogExpert.model.Category;
import com.joel.br.CatalogExpert.model.Product;
import com.joel.br.CatalogExpert.model.Role;
import com.joel.br.CatalogExpert.model.User;
import com.joel.br.CatalogExpert.repository.RoleRepository;
import com.joel.br.CatalogExpert.repository.UserDetailsProjection;
import com.joel.br.CatalogExpert.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServices implements UserDetailsService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper mapper;
    public Page<UserDTO> findAllPaged(Pageable pageable){
        Page<User> users = repository.findAll(pageable);


        return users.map(mapper::toEntity);
    }


    public UserDTO findById(Long id){
        User user = repository.findById(id).orElseThrow(() -> new  UserNotFoundException("user not found"));
        return mapper.toEntity(user);

    }


    public UserDTO save(UserInsertDTO dto){
        User user = new User();

        CopyToEntity(dto ,user);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return mapper.toEntity(repository.save(user));

    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }     catch (RuntimeException e)   {
            throw new UserNotFoundException("user not found");
        }
    }


    private void CopyToEntity(UserDTO userDTO , User user) {
            user.setId(userDTO.getId());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.getRoles().clear();


        for(RoleDTO userDTO1 :  userDTO.getRoles()) {
            Role category = roleRepository.getOne(userDTO1.getId());
            user.getRoles().add(category);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        List<UserDetailsProjection> result = repository.searchUsersAndRoleByEmail(username);

        if(result.size() == 0) {
            throw  new UsernameNotFoundException("user not found");
        }

        User user = new User();
        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());

        for(UserDetailsProjection us : result) {
            user.addRole(new Role(us.getRoleId(), us.getAuthority()));
        }


        return user;
    }
}
