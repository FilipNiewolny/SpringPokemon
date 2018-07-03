package com.pokemon.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemon.cache.PokemonCache;
import com.pokemon.dto.PokemonDto;
import com.pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@RestController
public class PokemonRest {


    RestTemplate restTemplate;
    PokemonService pokemonService;
    PokemonCache pokemonCache;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public PokemonRest(PokemonService pokemonService, RestTemplate restTemplate, PokemonCache pokemonCache) {
        this.pokemonService = pokemonService;
        this.restTemplate = restTemplate;
        this.pokemonCache = pokemonCache;

    }

    @RequestMapping(value = "/pokemon/{id}", method = RequestMethod.GET)
    public PokemonDto getPokemon(@PathVariable String id) throws IOException {
        PokemonDto response = pokemonService.getString(id, "http://pokeapi.co/api/v2/pokemon/");

        return response;
    }

    @RequestMapping(value = "/type/{orderId}", method = RequestMethod.GET)
    public String getType(@PathVariable String orderId) throws IOException {
        PokemonDto response = pokemonService.getString(orderId, "http://pokeapi.co/api/v2/type/");
        return response.toString();
    }

    @RequestMapping(value = "/ability/{orderId}", method = RequestMethod.GET)
    public String getAbility(@PathVariable String orderId) throws IOException {
        PokemonDto response = pokemonService.getString(orderId, "http://pokeapi.co/api/v2/ability/");
        return response.toString();
    }

    @PostMapping("/addPokemon")
    public ResponseEntity<String> addPokemon(@RequestBody PokemonDto pokemonDto){

        pokemonCache.pokemonDtoList.add(pokemonDto);

        return ResponseEntity.ok("ok");
    }

    @RequestMapping("/getPokemon")
    public List<PokemonDto> showPokemon() {

        return pokemonCache.pokemonDtoList;
    }



//    @RequestMapping(value = "/myPokemon/{id}", method = RequestMethod.GET)
//    public String getOurPokemon(@PathVariable String id) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        String ability = getAbility(id);
//        PokemonDto pokemonDto = mapper.readValues(ability, PokemonDto.class);
//        System.out.println(pokemonDto);
//
////        PokemonDto pokemonDto = new PokemonDto();
////        pokemonDto.setPokemon(restTemplate.getForObject("http://pokeapi.co/api/v2/pokemon/" + id , String.class));
////        JsonNode jsonNode = pokemonService.returnPokemon(pokemonDto);
////        return jsonNode.toString();
//    }


}
