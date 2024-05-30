package com.example.ejercicio_clase9.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private String fullname;
    @Column(nullable = false)
    private String username;
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private int status;
    @ManyToOne
    @JoinColumn(name = "idrol", nullable = false)
    private Roles idRol;
}
