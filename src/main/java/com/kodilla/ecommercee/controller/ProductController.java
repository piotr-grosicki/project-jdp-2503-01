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
        return List.of("Prod1", "Prod2", "Prod3", "Prod4");
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id) {
        return "Product with ID: " + id;
    }

    @PostMapping
    public String createProduct(@RequestBody String product) {
        return "Created product: " + product;
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody String product) {
        return "Updated product with ID " + id;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return "Deleted product with ID " + id;
    }
}
