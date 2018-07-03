package com.pokemon.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.dto.PokemonDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ParseService {


    public PokemonDto returnParsedPokemon(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(content, PokemonDto.class);
    }
}