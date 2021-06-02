package com.example.demo.repository;

import com.example.demo.domain.CreatePassengerRequest;
import com.example.demo.domain.Passenger;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PassengerRepository {

  private final JdbcTemplate jdbcTemplate;


  public Long createPassenger(CreatePassengerRequest request) {
    String sql = "insert into passenger(id_code, free_money)"
        + "values(?, ?) returning id";

    return jdbcTemplate.queryForObject(sql, Long.class, request.getIdCode(), request.getMoney());
  }

  public Passenger getPassengerId(Long busId) {
    String sql = "select * from passenger where id = ?";
    //RowMapper vaja siia teha!
    return jdbcTemplate.query(sql, );

  }
}
