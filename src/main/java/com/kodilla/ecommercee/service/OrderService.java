package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.controller.OrderNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;


    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }


    public Order findOrderById(final Long id) throws OrderNotFoundException {
        return orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);
    }

    public Order saveOrder(final Order order) {
        return orderRepository.save(order);
    }


    public Order updateOrder(final Long id, final OrderDto orderDto) throws OrderNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);

        order.setOrderDate(orderDto.getOrderDate());
        order.setStatus(orderDto.getStatus());


        userRepository.findById(orderDto.getUserId())
                .ifPresent(order::setUser);


        cartRepository.findById(orderDto.getCartId())
                .ifPresent(order::setCart);

        return orderRepository.save(order);
    }


    public void deleteOrder(final Long id) throws OrderNotFoundException {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException();
        }
        orderRepository.deleteById(id);
    }
}