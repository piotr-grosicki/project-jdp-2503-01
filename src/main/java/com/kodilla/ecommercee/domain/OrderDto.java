package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private LocalDate orderDate;
    private String status;
    private Long userId;
    private Long cartId;
}
