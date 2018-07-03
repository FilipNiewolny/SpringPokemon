package com.pokemon.service;

import com.pokemon.dto.PokemonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Service
public interface PokemonService {

    public PokemonDto getString(@PathVariable String orderId, String s) throws IOException;
//    public PokemonDto returnParsedPokemon(String s) throws IOException;
}
