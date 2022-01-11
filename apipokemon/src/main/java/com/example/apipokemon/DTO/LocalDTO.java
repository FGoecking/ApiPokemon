package com.example.apipokemon.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LocalDTO {

    private String logradouro;
    private String bairro;
    private String localidade;
    private String cep;
}
