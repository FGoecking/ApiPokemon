package com.example.apipokemon.controller;

import com.example.apipokemon.DTO.LocalDTO;
import com.example.apipokemon.DTO.PokemonDTO;
import com.example.apipokemon.model.PokemonCep;
import com.example.apipokemon.repository.PokemonCepRepository;
import com.example.apipokemon.service.PokemonService;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class PokemonCepController {

    @Autowired
    PokemonCepRepository pokemonCepRepository;

    PokemonService pokemonService;

    @GetMapping("/pokemon/{id}")
    public PokemonDTO getPokemonById(@PathVariable String id) throws IOException, InterruptedException {
       return pokemonService.getPokemonById(id);
    }

    @GetMapping("/ceppokemon/{idPokemon}")
    public List<PokemonCep> getCepPokemon(@PathVariable String idPokemon){
        return pokemonCepRepository.getAllByPokemon(idPokemon);
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("file")MultipartFile file) throws Exception {
        List<PokemonCep> pokemonCepList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings settings = new CsvParserSettings();

        settings.setHeaderExtractionEnabled(true);
        settings.setDelimiterDetectionEnabled(true);
        CsvParser parser = new CsvParser(settings);
        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
        parseAllRecords.forEach(record -> {
            PokemonCep pokemonCep = new PokemonCep();
            pokemonCep.setPokemon(record.getString("POKEMON"));
            pokemonCep.setCep(record.getString("CEP"));
            pokemonCepList.add(pokemonCep);
        });
        pokemonCepRepository.saveAll(pokemonCepList);
        return  ResponseEntity.ok().body("Upload realizado com sucesso!!!");
    }
}
