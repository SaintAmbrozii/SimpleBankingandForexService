package com.example.bankingservice.service;

import com.example.bankingservice.domain.Role;
import com.example.bankingservice.domain.User;
import com.example.bankingservice.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;


    public boolean createUser(User user) {
        if (user!=null){
            user.setName(user.getName());
            user.setAktive(true);
            user.setEmail(user.getEmail());
            user.setLastname(user.getLastname());
            user.setPassword(user.getPassword());
            user.setRoles(Collections.singleton(Role.USER));
            userRepo.save(user);
        }
        return true;
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
