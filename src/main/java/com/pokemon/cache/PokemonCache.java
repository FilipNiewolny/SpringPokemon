package com.pokemon.cache;

import com.pokemon.dto.PokemonDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonCache {

    public List<PokemonDto> pokemonDtoList;

    @PostConstruct
    public void methodInit() {
        pokemonDtoList = new ArrayList<>();
    }


    //SIMPLE EXECUTE
    //// jdbcTemplate.execute("create table user (id int, name varchar)");

    //SIMPLE UPDATE
//    public int addEmplyee(int id) {
//        return jdbcTemplate.update(
//                "INSERT INTO EMPLOYEE VALUES (?, ?, ?, ?)", 5, "Bill", "Gates", "USA");
//    }


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


    //https://docs.spring.io/spring/docs/5.0.7.RELEASE/spring-framework-reference/data-access.html#jdbc
}

