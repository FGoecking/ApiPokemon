package com.example.apipokemon.service;


import com.example.apipokemon.DTO.PokemonDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

@Service
public class PokemonService {



    private static final String BASEURL = "https://pokeapi.co/api/v2/pokemon/";

    public PokemonDTO getPokemonById(String id) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASEURL + id + "/"))
                .build();

        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        response.statusCode();
        ObjectMapper mapper = new ObjectMapper();
        List<PokemonDTO> pokemonDTOList = mapper.readValue(response.body(), new TypeReference<List<PokemonDTO>>() {
        });

        return pokemonDTOList.get(0);
    }


}
