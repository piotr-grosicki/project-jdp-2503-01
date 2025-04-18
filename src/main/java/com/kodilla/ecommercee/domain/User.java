package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;
}
