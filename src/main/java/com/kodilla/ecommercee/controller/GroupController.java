package com.kodilla.ecommercee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/groups")
@CrossOrigin("*")
@RequiredArgsConstructor
public class GroupController {

    @GetMapping
    public List<String> getAllGroups() {
        return List.of("Laptops", "Smartphones", "Accessories");
    }

    @GetMapping("/{id}")
    public String getGroupById(@PathVariable Long id) {
        return "Group ID: " + id + ", Name: Laptops";
    }

    @PostMapping
    public String createGroup(@RequestBody String group) {
        return "Created group: " + group;
    }

    @PutMapping("/{id}")
    public String updateGroup(@PathVariable Long id, @RequestBody String group) {
        return "Updated group with ID " + id + ": " + group;
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable Long id) {
        return "Deleted group with ID: " + id;
    }
}
