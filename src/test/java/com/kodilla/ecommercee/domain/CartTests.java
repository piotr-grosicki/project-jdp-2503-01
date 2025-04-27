package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CartTests {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    private User user;
    private Product product;

    @BeforeEach
    void setup() {
        user = userRepository.save(new User(null, "Robert", "robert@com", "password", false, new ArrayList<>()));
        product = productRepository.save(new Product(null, "Laptop", new BigDecimal("3999.99"), "Gaming laptop", null));
    }

    @Test
    @DisplayName("Should create cart with user and products")
    void testCreateCart() {
        // given
        Cart cart = new Cart(null, LocalDateTime.now(), user, List.of(product));

        // when
        Cart savedCart = cartRepository.save(cart);

        // then
        Optional<Cart> readCart = cartRepository.findById(savedCart.getCartId());
        assertTrue(readCart.isPresent());
        assertEquals(user.getUserId(), readCart.get().getUser().getUserId());
        assertEquals(1, readCart.get().getProducts().size());
        assertEquals("Laptop", readCart.get().getProducts().get(0).getName());
    }

    @Test
    @DisplayName("Should update cart by adding more products")
    void testUpdateCart() {
        // given
        Product extraProduct = productRepository.save(new Product(null, "Tablet", new BigDecimal("2999.99"), "Tablet device", null));
        Cart cart = cartRepository.save(new Cart(null, LocalDateTime.now(), user, new ArrayList<>(List.of(product))));

        // when
        cart.getProducts().add(extraProduct);
        Cart updatedCart = cartRepository.save(cart);

        // then
        assertEquals(2, updatedCart.getProducts().size());
    }

    @Test
    @DisplayName("Should delete cart but keep user and products")
    void testDeleteCart() {
        // given
        Cart cart = cartRepository.save(new Cart(null, LocalDateTime.now(), user, List.of(product)));
        Long cartId = cart.getCartId();

        // when
        cartRepository.deleteById(cartId);

        // then
        assertFalse(cartRepository.findById(cartId).isPresent());
        assertTrue(userRepository.findById(user.getUserId()).isPresent());
        assertTrue(productRepository.findById(product.getProductId()).isPresent());
    }

    @Test
    @DisplayName("Should find cart by ID and verify contents")
    void testReadCart() {
        // given
        Cart cart = cartRepository.save(new Cart(null, LocalDateTime.now(), user, List.of(product)));

        // when
        Optional<Cart> result = cartRepository.findById(cart.getCartId());

        // then
        assertTrue(result.isPresent());
        assertEquals("Robert", result.get().getUser().getUsername());
        assertEquals("Laptop", result.get().getProducts().get(0).getName());
    }

    @Test
    @DisplayName("Should create order from cart and link them")
    void testCartOrderRelation() {
        // given
        Cart cart = cartRepository.save(new Cart(null, LocalDateTime.now(), user, new ArrayList<>()));

        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setStatus("CREATED");
        order.setUser(user);
        order.setCart(cart);

        // when
        Order savedOrder = orderRepository.save(order);

        // then
        assertNotNull(savedOrder.getOrderId());
        assertEquals(cart.getCartId(), savedOrder.getCart().getCartId());
        assertEquals(user.getUserId(), savedOrder.getUser().getUserId());
    }
}
