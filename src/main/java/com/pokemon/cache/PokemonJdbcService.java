package com.pokemon.cache;

import com.pokemon.dto.PokemonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PokemonJdbcService {

    public List<PokemonDto> pokemonDtoList;

    @Autowired
    JdbcTemplate jdbcTemplate;

    int id = 0;

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
                "INSERT INTO poke VALUES (?,?,?,?,?,?,?)",
                id++,
                pokemonDto.getName(),
                pokemonDto.getWeight(),
                pokemonDto.getSpeciesUrl(),
                pokemonDto.getSpeciesName(),
                pokemonDto.getStats().toString(),
                pokemonDto.getAbilities().toString()


        );


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
}

