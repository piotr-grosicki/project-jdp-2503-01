package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, OrderDto orderDto) throws Exception {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new Exception("Order not found"));

        existingOrder.setOrderDate(orderDto.getOrderDate() != null ? orderDto.getOrderDate() : LocalDate.now());
        existingOrder.setStatus(orderDto.getStatus());
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) throws Exception {
        if (!orderRepository.existsById(id)) {
            throw new Exception("Order not found");
        }
        orderRepository.deleteById(id);
    }
}