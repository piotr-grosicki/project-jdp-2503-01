package com.kodilla.ecommercee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping
    public List<String> getAllProducts() {
        return List.of("Laptop", "Smartphone", "Tablet");
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id) {
        return "Product with ID: " + id;
    }

    @PostMapping
    public String createProduct(@RequestBody String product) {
        return "Product created: " + product;
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody String product) {
        return "Product with ID " + id + " updated to: " + product;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return "Product with ID " + id + " deleted.";
    }
}
