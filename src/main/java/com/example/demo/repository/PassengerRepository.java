package com.example.demo.repository;

import com.example.demo.domain.create_requests.CreatePassengerRequest;
import com.example.demo.domain.Passenger;
import com.example.demo.repository.row_mappers.PassengerRowMapper;
import java.util.List;
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

  public Passenger getPassengerId(Long passengerId) {
    String sql = "select * from passenger where id = ?";

    return jdbcTemplate.query(sql, new PassengerRowMapper(), passengerId)
        .stream().findFirst().orElse(null);
  }

  public void deletePassengerById(Long id) {
    String sql = "delete from passenger where id = ?";

    jdbcTemplate.update(sql, id);
  }

  public List<Passenger> getAllPassengers() {
    String sql = "Select * from passenger";

    return jdbcTemplate.query(sql, new PassengerRowMapper());
  }

  public void updatePassenger(Passenger passenger) {
    String sql = "update passenger set free_money = ?, id_code = ?  where id = ?";

    jdbcTemplate.update(sql, passenger.getMoney(), passenger.getIdCode(), passenger.getId());
  }
}