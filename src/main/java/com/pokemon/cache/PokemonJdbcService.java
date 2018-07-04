package com.pokemon.cache;

import com.pokemon.dto.AbilitiesDto;
import com.pokemon.dto.PokemonDto;
import com.pokemon.dto.StatsDto;
import com.pokemon.rest.PokemonRest;
import com.pokemon.service.ParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PokemonJdbcService {

    public List<PokemonDto> pokemonDtoList;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PokemonRest pokemonRest;

    @Autowired
    ParseService parseService;

    public List<Map<String, Object>> getAllPokemon() {
        return jdbcTemplate.queryForList("SELECT * FROM POKE ");
    }

    @PostConstruct
    public void methodInit() {
        pokemonDtoList = new ArrayList<>();
    }


    //SIMPLE EXECUTE
    //// jdbcTemplate.execute("create table user (id int, name varchar)");

    //SIMPLE UPDATE
    public void addPokemon(PokemonDto pokemonDto) {
        this.jdbcTemplate.update(
                "INSERT INTO poke(pokeId ,name, WEIGHT, SPECIESURL, SPECIESNAME, STATS, ABILITIES) VALUES (?,?,?,?,?,?,?)",
                pokemonDto.getId(),
                pokemonDto.getName(),
                pokemonDto.getWeight(),
                pokemonDto.getSpeciesUrl(),
                pokemonDto.getSpeciesName(),
                pokemonDto.getStats().toString(),
                pokemonDto.getAbilities().toString()


        );


    }

    public PokemonDto getPokemonById(int id) throws IOException {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM POKE Where pokeId =" + id);
        PokemonDto pokemonDto= new PokemonDto();
        if (!maps.isEmpty()) {

            Map<String, Object> stringObjectMap = maps.get(0);
            int pokeid = (int) stringObjectMap.get("POKEID");
            pokemonDto.setId(pokeid);
            pokemonDto.setName((String) stringObjectMap.get("name"));
            pokemonDto.setWeight((String) stringObjectMap.get("weight"));
            AbilitiesDto[] abilities = new AbilitiesDto[0];
            pokemonDto.setAbilities(abilities);
            pokemonDto.setSpeciesName((String) stringObjectMap.get("speciesName"));
            pokemonDto.setSpeciesUrl((String) stringObjectMap.get("speciesUrl"));
            pokemonDto.setStats(new StatsDto[0]);
        }
        else {
            pokemonDto = pokemonRest.getPokemon(String.valueOf(id));
            this.addPokemon(pokemonDto);
        }
        return pokemonDto;
    }



    }


    //MAP PARAMETERS
//
//    SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", 1);
//return namedParameterJdbcTemplate.queryForObject(
//        "SELECT FIRST_NAME FROM EMPLOYEE WHERE ID = :id", namedParameters, String.class);


    // ROWMAPPER
//    public class EmployeeRowMapper implements RowMapper<Employee> {
////        @Override
////        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
////            Employee employee = new Employee();
////
////            employee.setId(rs.getInt("ID"));
////            employee.setFirstName(rs.getString("FIRST_NAME"));
////            employee.setLastName(rs.getString("LAST_NAME"));
////            employee.setAddress(rs.getString("ADDRESS"));
////
////            return employee;
////        }
////    }
//String query = "SELECT * FROM EMPLOYEE WHERE ID = ?";
//    List<Employee> employees = jdbcTemplate.queryForObject(
//            query, new Object[] { id }, new EmployeeRowMapper());


//    https://docs.spring.io/spring/docs/5.0.7.RELEASE/spring-framework-reference/data-access.html#jdbc


