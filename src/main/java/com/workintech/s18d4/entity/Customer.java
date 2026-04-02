package com.workintech.s18d4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private  String email;
    private Double salary;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="address_id")
    private Address address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Account> accounts =  new ArrayList<>();
}
