package com.example.apipokemon.service;


import com.example.apipokemon.DTO.LocalDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class LocalService {

    private final WebClient webClient;

    public LocalService(WebClient.Builder builder) {
        webClient = builder.baseUrl("viacep.com.br/ws/").build();
    }

    public Mono<LocalDTO> findLocalbyCep(String cep){

        return webClient.get()
                .uri(cep + "/json/")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("Verifique os parametros informados")))
                .bodyToMono(LocalDTO.class);
    }
}
