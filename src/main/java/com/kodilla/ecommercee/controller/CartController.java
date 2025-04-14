package com.kodilla.ecommercee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/carts")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CartController {

    @GetMapping
    public List<String> getAllCarts() {
        return List.of("Cart 1 (User ID: 1)", "Cart 2 (User ID: 2)");
    }

    @GetMapping("/{id}")
    public String getCartById(@PathVariable Long id) {
        return "Cart ID: " + id + ", Created At: 2025-14-04, User ID: 1";
    }

    @PostMapping
    public String createCart(@RequestBody String cart) {
        return "Created new cart: " + cart;
    }

    @PutMapping("/{id}")
    public String updateCart(@PathVariable Long id, @RequestBody String cart) {
        return "Updated cart ID " + id + " with new data: " + cart;
    }

    @DeleteMapping("/{id}")
    public String deleteCart(@PathVariable Long id) {
        return "Deleted cart with ID: " + id;
    }
}
