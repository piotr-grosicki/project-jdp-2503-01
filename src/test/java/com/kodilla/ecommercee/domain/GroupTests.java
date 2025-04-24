package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GroupTests {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    private Group testGroup;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setup() {
        testGroup = groupRepository.save(new Group(null, "Electronics", "Devices and gadgets", new ArrayList<>()));
        product1 = productRepository.save(new Product(null, "Smartphone", new BigDecimal("1999.99"), "Latest model", testGroup));
        product2 = productRepository.save(new Product(null, "Laptop", new BigDecimal("3999.99"), "Gaming laptop", testGroup));
    }

    @Test
    @DisplayName("Should create a new group")
    void testCreateGroup() {
        Group group = new Group(null, "Books", "All kinds of books", List.of());
        Group savedGroup = groupRepository.save(group);

        assertNotNull(savedGroup.getGroupId());
        assertEquals("Books", savedGroup.getName());
    }

    @Test
    @DisplayName("Should read group by ID")
    void testReadGroup() {
        Optional<Group> result = groupRepository.findById(testGroup.getGroupId());

        assertTrue(result.isPresent());
        assertEquals("Electronics", result.get().getName());
    }

    @Test
    @DisplayName("Should update group description")
    void testUpdateGroup() {
        testGroup.setDescription("Updated electronics category");
        Group updatedGroup = groupRepository.save(testGroup);

        assertEquals("Updated electronics category", updatedGroup.getDescription());
    }

    @Test
    @DisplayName("Should delete group and keep products")
    void testDeleteGroup() {
        groupRepository.delete(testGroup);

        assertFalse(groupRepository.findById(testGroup.getGroupId()).isPresent());
        assertTrue(productRepository.findById(product1.getProductId()).isPresent());
        assertTrue(productRepository.findById(product2.getProductId()).isPresent());
    }

    @Test
    @DisplayName("Should fetch products assigned to group")
    void testGroupProductRelation() {
        testGroup.getProducts().add(product1);
        testGroup.getProducts().add(product2);
        groupRepository.save(testGroup);

        Group fetched = groupRepository.findById(testGroup.getGroupId()).orElseThrow();

        assertEquals(2, fetched.getProducts().size());
        assertEquals("Smartphone", fetched.getProducts().get(0).getName());
    }
}
