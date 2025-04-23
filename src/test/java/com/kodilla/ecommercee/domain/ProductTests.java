package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Test
    @DisplayName("Should create and find product by ID")
    void testCreateAndReadProduct() {
        // given
        Group group = new Group(null, "Electronics", "Devices and gadgets", null);
        groupRepository.save(group);

        Product product = new Product(null, "Smartphone", new BigDecimal("1299.99"), "Latest model", group);

        // when
        productRepository.save(product);
        Optional<Product> found = productRepository.findById(product.getProductId());

        // then
        assertTrue(found.isPresent());
        assertEquals("Smartphone", found.get().getName());
        assertEquals("Electronics", found.get().getGroupId().getName());
    }

    @Test
    @DisplayName("Should update product details")
    void testUpdateProduct() {
        // given
        Group group = new Group(null, "Books", "Book category", null);
        groupRepository.save(group);

        Product product = new Product(null, "Java Book", new BigDecimal("49.99"), "Learn Java", group);
        productRepository.save(product);

        // when
        product.setName("Advanced Java Book");
        product.setPrice(new BigDecimal("59.99"));
        productRepository.save(product);

        // then
        Optional<Product> updated = productRepository.findById(product.getProductId());
        assertTrue(updated.isPresent());
        assertEquals("Advanced Java Book", updated.get().getName());
        assertEquals(new BigDecimal("59.99"), updated.get().getPrice());
    }

    @Test
    @DisplayName("Should delete product")
    void testDeleteProduct() {
        // given
        Group group = new Group(null, "Gaming", "Gaming category", null);
        groupRepository.save(group);

        Product product = new Product(null, "Console", new BigDecimal("1999.99"), "PlayStation 5", group);
        productRepository.save(product);
        Long productId = product.getProductId();

        // when
        productRepository.deleteById(productId);
        Optional<Product> deleted = productRepository.findById(productId);

        // then
        assertFalse(deleted.isPresent());
    }

    @Test
    @DisplayName("Deleting product should not delete its group")
    void testGroupPersistenceAfterProductDeletion() {
        // given
        Group group = new Group(null, "Audio", "Sound systems", null);
        groupRepository.save(group);

        Product product = new Product(null, "Headphones", new BigDecimal("199.99"), "Wireless", group);
        productRepository.save(product);

        // when
        productRepository.delete(product);

        // then
        Optional<Group> stillExists = groupRepository.findById(group.getGroupId());
        assertTrue(stillExists.isPresent());
        assertEquals("Audio", stillExists.get().getName());
    }
}


