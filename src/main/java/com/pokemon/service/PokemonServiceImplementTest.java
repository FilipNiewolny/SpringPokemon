package com.pokemon.service;

import com.pokemon.dto.PokemonDto;

import java.io.IOException;

public class PokemonServiceImplementTest implements PokemonService {
    @Override
    public PokemonDto getString(String orderId, String s) {
        return null;
    }

    public PokemonDto returnParsedPokemon(String s) throws IOException {
        return new PokemonDto("bulbasaur" , "20");
    }
}
