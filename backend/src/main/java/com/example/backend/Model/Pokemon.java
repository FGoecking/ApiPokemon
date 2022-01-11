package com.example.backend.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Pokemon {

    @Id
    @SequenceGenerator(name = "pokemon_sequence", sequenceName = "pokemon_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokemon_sequence")
    private Long id;

    private String pokemon;

    private String cep;


}
