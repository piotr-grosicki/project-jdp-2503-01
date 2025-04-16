package com.kodilla.ecommercee.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/carts")
@RequiredArgsConstructor
public class CartController {
    @GetMapping
    public List<String> getAllCarts() {
        return List.of("Cart 1", "Cart 2", "Cart 3");
    }
    @GetMapping("/{id}")
    public String getCartById(@PathVariable Long id) {
        return "Cart wth ID: " + id;
    }
    @PostMapping
    public String createCart(@RequestBody String cart) {
        return "Cart created: " + cart;
    }
    @PutMapping("/{id}")
    public String updateCart(@PathVariable Long id, @RequestBody String cart) {
        return "Cart with ID: " + id + " updated: " + cart;
    }
    @DeleteMapping("/{id}")
    public String deleteCart(@PathVariable Long id) {
        return "Cart with ID: " + id + " deleted";
    }
}
