package com.pokemon.service;

import com.pokemon.cache.PokemonJdbcService;
import com.pokemon.dto.PokemonDto;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    ParseService parseService;
    @Autowired
    PokemonJdbcService pokemonJdbcService;

    public PokemonDto getString(@PathVariable String orderId, String s) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        ResponseEntity<String> response
                = new RestTemplate(requestFactory).exchange(
                s + orderId, HttpMethod.GET, null, String.class);
        return parseService.returnParsedPokemon(response.getBody());
    }

    @Override
    public void addToDb(PokemonDto pokemonDto) {
        pokemonJdbcService.addPokemon(pokemonDto);
    }

    @Override
    public List<Map<String, Object>> getAllPokemon() {
        return pokemonJdbcService.getAllPokemon();
    }


//    public PokemonDto returnParsedPokemon(String s) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        return mapper.readValue(s, PokemonDto.class);
//    }
}
