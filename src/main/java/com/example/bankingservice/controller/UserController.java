package com.example.bankingservice.controller;

import com.example.bankingservice.domain.User;
import com.example.bankingservice.dto.UserDto;
import com.example.bankingservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("profile")
    public User getProfile(@AuthenticationPrincipal User user) {
        return userService.getByEmail(user.getEmail()).orElseThrow();
    }
    @PostMapping()
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }
    @GetMapping()
    public List<User> gatAllUsers() {
       return userService.getAllUsers();
    }
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable(name = "id") User user) {
        userService.deleteUser(user);
    }
    @GetMapping("{id}")
    public Optional<User> findById(@PathVariable(name = "id") Long id) {
        return userService.findById(id);
    }
    @PutMapping("{id}")
    public User updUser(@PathVariable(name = "id")Long id,@RequestBody User user) {
       return userService.updateUser(id,user);
    }
}
