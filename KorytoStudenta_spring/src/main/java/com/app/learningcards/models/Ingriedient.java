package com.app.learningcards.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Ingredients")
@Data
public class Ingriedient
{
    @Id
    @SequenceGenerator(
            name="ingriedient_sequence",
            sequenceName="ingriedient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ingriedient_sequence"
    )
    @Column(
            name="id",
            updatable = false
    )
    public Long id;
    private String name;
}