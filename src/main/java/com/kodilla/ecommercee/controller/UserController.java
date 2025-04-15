package com.kodilla.ecommercee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping
    public List<String> getAllUsers() {
        return List.of("John Doe", "Jane Smith", "Alice Johnson");
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id) {
        return "User with ID: " + id;
    }

    @PostMapping
    public String createUser(@RequestBody String user) {
        return "User created: " + user;
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody String user) {
        return "User with ID " + id + " updated to: " + user;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "User with ID " + id + " deleted.";
    }
}