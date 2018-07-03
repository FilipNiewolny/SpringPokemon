package com.pokemon.app.integration;


import com.pokemon.dto.PokemonDto;
import com.pokemon.service.PokemonService;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PokemonDtoTest {

    @Autowired
    TestRestTemplate testRestTemplate;
    PokemonService pokemonService;

    @Test
    public void shouldGetResponseBody() {
        PokemonDto body = this.testRestTemplate.getForObject("/pokemon/1", PokemonDto.class);
        assertThat(body.getName()).isEqualTo("bulbasaur");
        assertThat(body.getAbilities()[0].getSlot()).isEqualTo(3);
        assertThat(body.getSpeciesName()).isEqualTo("bulbasaur");
        assertThat(body.getStats()[0].getBase_stat()).isEqualTo("45");
    }

    @Test
    public void shouldGetResponseFalseBody() {
        PokemonDto body = this.testRestTemplate.getForObject("/pokemon/1", PokemonDto.class);
        assertThat(body.getName()).isNotEqualTo("bubasaur");
        assertThat(body.getAbilities()[0].getSlot()).isNotEqualTo(43);
        assertThat(body.getSpeciesName()).isNotEqualTo("bulbasar");
        assertThat(body.getStats()[0].getBase_stat()).isNotEqualTo("55");

    }


    @Test
    public void shouldGetStatusError() throws IOException {
        ResponseEntity<PokemonDto> forEntity = testRestTemplate.getForEntity("/pokemn/1", PokemonDto.class);
        assertThat(forEntity.getStatusCode().is4xxClientError()).isTrue();
    }

    @Test
    public void shouldGetStatusOk() throws IOException {
        ResponseEntity<PokemonDto> forEntity = testRestTemplate.getForEntity("/pokemon/1", PokemonDto.class);
        assertThat(forEntity.getStatusCode().is2xxSuccessful()).isTrue();
    }


}
