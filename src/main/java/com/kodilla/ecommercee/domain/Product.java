package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "price")
    private Float price;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group groupId;
}
