package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    void testCreateOrderWithUserAndCart() {
        //Given
        User user = userRepository.save(new User(null, "order_user", "email@email.com", "pass123", false, null));
        Cart cart = new Cart(null, new Date(), user, new ArrayList<>());
        cart = cartRepository.save(cart);

        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setStatus("NEW");
        order.setUser(user);
        order.setCart(cart);

        //When
        Order savedOrder = orderRepository.save(order);

        //Then
        assertNotNull(savedOrder.getOrderId());
        assertEquals("NEW", savedOrder.getStatus());
        assertEquals(user.getUserId(), savedOrder.getUser().getUserId());
        assertEquals(cart.getCartId(), savedOrder.getCart().getCartId());
    }

    @Test
    void testReadOrder() {
        //Given
        User user = userRepository.save(new User(null, "reader", "email@email.com", "pass123", false, null));
        Cart cart = cartRepository.save(new Cart(null, new Date(), user, new ArrayList<>()));
        Order order = orderRepository.save(new Order(null, LocalDate.now(), "PENDING", user, cart));

        //When
        Optional<Order> readOrder = orderRepository.findById(order.getOrderId());

        //Then
        assertTrue(readOrder.isPresent());
        assertEquals("PENDING", readOrder.get().getStatus());
        assertEquals(user.getUserId(), readOrder.get().getUser().getUserId());
        assertEquals(cart.getCartId(), readOrder.get().getCart().getCartId());
    }

    @Test
    void testUpdateOrderStatus() {
        //Given
        User user = userRepository.save(new User(null, "updater", "email@email.com", "pass123", false, null));
        Cart cart = cartRepository.save(new Cart(null, new Date(), user, new ArrayList<>()));
        Order order = orderRepository.save(new Order(null, LocalDate.now(), "WAITING", user, cart));

        //When
        order.setStatus("SHIPPED");
        Order updateOrder = orderRepository.save(order);

        //Then
        assertEquals("SHIPPED", updateOrder.getStatus());
    }

    @Test
    void testDeleteOrder() {
        //Given
        User user = userRepository.save(new User(null, "delete", "email@email.com", "pass123", false, null));
        Cart cart = cartRepository.save(new Cart(null, new Date(), user, new ArrayList<>()));
        Order order = orderRepository.save(new Order(null, LocalDate.now(), "TO_DELETE", user, cart));
        Long orderId = order.getOrderId();

        //When
        orderRepository.delete(order);

        //Then
        assertFalse(orderRepository.findById(orderId).isPresent());
        assertTrue(userRepository.findById(user.getUserId()).isPresent());
        assertTrue(cartRepository.findById(cart.getCartId()).isPresent());
    }
}
