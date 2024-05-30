package com.example.ejercicio_clase9.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "characters")
public class Characters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String url;
    private String identity;
    private String align;
    private String eye;
    private String hair;
    private String sex;
    private String gsm;
    private String alive;
    private int appearances;
    @Column(name = "first_appearance")
    private String firstAppearance;
    private int year;
}
