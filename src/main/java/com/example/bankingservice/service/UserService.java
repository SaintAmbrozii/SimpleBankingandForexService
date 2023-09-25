package com.example.bankingservice.service;

import com.example.bankingservice.domain.Role;
import com.example.bankingservice.domain.User;
import com.example.bankingservice.dto.UserDto;
import com.example.bankingservice.exception.UserIsExist;
import com.example.bankingservice.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    private final PasswordEncoder encoder;


    public User createUser(UserDto userDto) {
        Optional <User> inDB = userRepo.findByEmail(userDto.getEmail());
        if (inDB.isPresent()) {
            throw new UserIsExist("пользователь с таким емайл уже есть");
        }
        User user = new User();
        user.setAktive(true);
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        return  userRepo.save(user);

    }
    public void deleteUser(User user) {
        userRepo.delete(user);
    }
    public Optional<User> findById(Long id){
        return userRepo.findById(id);
    }
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User updateUser(Long id, User user){
        User inDB = userRepo.findById(id).orElseThrow();
        inDB.setName(user.getName());
        inDB.setPassword(user.getPassword());
        inDB.setLastname(user.getLastname());
        inDB.setEmail(user.getEmail());
        return userRepo.save(inDB);
    }
    public Optional<User> getByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User authentificational(Principal principal) {
        Authentication authentication = (Authentication) principal;
        User user = (User) authentication.getPrincipal();
       return user;
    }
    public User getCurrentUser() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            return user;

    }
}
