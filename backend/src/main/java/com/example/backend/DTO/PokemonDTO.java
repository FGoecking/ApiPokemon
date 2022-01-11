package com.example.backend.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PokemonDTO {

    private String id;
    private String name;
    private Integer weight;
    private Integer height;

}
