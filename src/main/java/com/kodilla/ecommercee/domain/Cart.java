package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "cart")
public class Cart {


    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
