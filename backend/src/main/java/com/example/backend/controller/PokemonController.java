package com.example.backend.controller;

import com.example.backend.DTO.PokemonDTO;
import com.example.backend.Service.PokemonService;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class PokemonController {

//    @Autowired
//    PokemonRepository pokemonRepository;

    PokemonService pokemonService;

    @GetMapping("/pokemon/{id}")
    public Mono<PokemonDTO> getPokemonById(@PathVariable String id){
        Mono pokemon = pokemonService.findPokemonById(id);
        return pokemon;
    }

//    @PostMapping("/pokemon/upload")
//    public String uploadData(@RequestParam("file")MultipartFile file) throws Exception{
//        List<Pokemon> pokemonList = new ArrayList<>();
//        InputStream inputStream = file.getInputStream();
//        CsvParserSettings settings = new CsvParserSettings();
//
//        settings.setHeaderExtractionEnabled(true);
//        CsvParser parser = new CsvParser(settings);
//        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
//        parseAllRecords.forEach(record -> {
//            Pokemon pokemon = new Pokemon();
//            pokemon.setId(Long.valueOf(record.getString("ID")));
//            pokemon.setNome(record.getString("NOME"));
//            pokemonList.add(pokemon);
//        });
//        pokemonRepository.saveAll(pokemonList);
//        return "Upload realizado com sucesso!!!";
//    }
}
