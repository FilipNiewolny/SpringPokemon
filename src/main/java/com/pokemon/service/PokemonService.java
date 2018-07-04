package com.pokemon.service;

import com.pokemon.dto.PokemonDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface PokemonService {

    public PokemonDto getString(@PathVariable String orderId, String s) throws IOException;

    void addToDb(PokemonDto pokemonDto);

    List<Map<String, Object>> getAllPokemon();

    PokemonDto getPokemonById(int id) throws IOException;
//    public PokemonDto returnParsedPokemon(String s) throws IOException;
}
