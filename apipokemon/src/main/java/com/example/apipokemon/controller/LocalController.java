package com.example.apipokemon.controller;

import com.example.apipokemon.DTO.LocalDTO;
import com.example.apipokemon.service.LocalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class LocalController {

    LocalService localService;

    @GetMapping("/cep/{cep}")
    public Mono<LocalDTO> getLocalByCep(@PathVariable String cep){
        return localService.findLocalbyCep(cep);
    }

}
