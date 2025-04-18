package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "groupId", fetch = FetchType.LAZY)
    private List<Product> products;
}
