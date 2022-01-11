package com.example.backend.Service;

import com.example.backend.DTO.PokemonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PokemonService {

    private final WebClient webClient;

    public PokemonService(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://pokeapi.co/api/v2").build();
    }



    public Mono<PokemonDTO> findPokemonById(String id){
        log.info("Buscando pokemon por ID [{}]", id);
        return webClient.get()
                .uri("/pokemon/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("Verifique os parametros informados.")))
                .bodyToMono(PokemonDTO.class);
    }
}
