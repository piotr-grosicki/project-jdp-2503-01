package com.kodilla.ecommercee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    @GetMapping
    public List<String> getAllOrders() {
        return List.of("Order 1", "Order 2", "Order 3", "Order 4");
    }

    @GetMapping("/{id}")
    public String getOrderById(@PathVariable Long id) {
        return "Order with ID: " + id;
    }

    @PostMapping
    public String createOrder(@RequestBody String order) {
        return "Created order: " + order;
    }

    @PutMapping("/{id}")
    public String updateOrder(@PathVariable Long id, @RequestBody String order) {
        return "Updated order with ID " + id + " to: " + order;
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        return "Deleted order with ID: " + id;
    }
}