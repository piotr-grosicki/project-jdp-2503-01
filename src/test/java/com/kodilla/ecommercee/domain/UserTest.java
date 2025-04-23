package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Create a user and save it in the database.")
    void testCreateUser() {
        // Given
        User user = new User(null, "Robert", "robert@email.com", "password1", false, List.of());

        // When
        User savedUser = userRepository.save(user);

        // Then
        assertNotNull(savedUser.getUserId());
        assertEquals("Robert", savedUser.getUsername());
        assertEquals("robert@email.com", savedUser.getEmail());
    }

    @Test
    @DisplayName("The test saves the user and we read it by ID.")
    void testReadUser() {
        // Given
        User user = new User(null, "Piotr", "piotr@email.com", "password2", false, List.of());
        userRepository.save(user);

        // When
        Optional<User> retrievedUser = userRepository.findById(user.getUserId());

        // Then
        assertTrue(retrievedUser.isPresent());
        assertEquals("Piotr", retrievedUser.get().getUsername());
    }

    @Test
    @DisplayName("The test changes the user's email and checks whether the update has been saved.")
    void testUpdateUser() {
        // Given
        User user = new User(null, "admin", "admin@email.com", "password3", false, List.of());
        User savedUser = userRepository.save(user);

        // When
        savedUser.setEmail("admin2@email.com");
        User updatedUser = userRepository.save(savedUser);

        // Then
        assertEquals("admin2@email.com", updatedUser.getEmail());
    }

    @Test
    @DisplayName("The test removes the user and verifies that the user has been correctly removed from the database.")
    void testDeleteUser() {
        // Given
        User user = new User(null, "to_delete", "delete@email.com", "password4", false, List.of());
        userRepository.save(user);

        // When
        userRepository.deleteById(user.getUserId());
        Optional<User> deleted = userRepository.findById(user.getUserId());

        // Then
        assertFalse(deleted.isPresent());
    }

    @Test
    @DisplayName("Test checks relationships User -> Order")
    void testUserWithOrdersRelationship() {
        // Given
        Order order1 = new Order(); order1.setStatus("NEW");
        Order order2 = new Order(); order2.setStatus("PROCESSING");
        User user = new User(null, "admin", "admin@email.com", "password5", false, List.of(order1, order2));

        order1.setUser(user);
        order2.setUser(user);

        // When
        User savedUser = userRepository.save(user);

        // Then
        assertEquals(2, savedUser.getOrders().size());
        assertTrue(savedUser.getOrders().stream().anyMatch(order -> order.getStatus().equals("NEW")));
    }
}