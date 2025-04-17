package com.kodilla.ecommercee;

import com.kodilla.ecommercee.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EcommerceeApplicationTests {

    private final UserController userController = new UserController();

    @Test
    void shouldReturnAllUsers() {
        // when
        List<String> users = userController.getAllUsers();
        // then
        assertEquals(3, users.size());
        assertTrue(users.contains("John Doe"));
        assertTrue(users.contains("Jane Smith"));
        assertTrue(users.contains("Alice Johnson"));
    }

    @Test
    void shouldReturnUserById() {
        // when
        String result = userController.getUserById(7L);
        // then
        assertEquals("User with ID: 7", result);
    }

    @Test
    void shouldCreateUser() {
        // when
        String result = userController.createUser("Test User");
        // then
        assertEquals("User created: Test User", result);
    }

    @Test
    void shouldUpdateUser() {
        // when
        String result = userController.updateUser(2L, "Updated User");
        // then
        assertEquals("User with ID 2 updated to: Updated User", result);
    }

    @Test
    void shouldDeleteUser() {
        // when
        String result = userController.deleteUser(4L);
        // then
        assertEquals("User with ID 4 deleted.", result);
    }
    @Test
    public void contextLoads() {
    }

}

