package com.example.demo.service;

import com.example.demo.domain.BoxOffice;
import com.example.demo.domain.CreateBoxOfficeRequest;
import com.example.demo.repository.rowMappers.BoxOfficeRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoxOfficeRepository {

  private final JdbcTemplate jdbcTemplate;

  public BoxOffice getBoxOfficeByPassengerIdAndBusId(Long passengerId, Long busId, Long boxOfficeId) {
    String sql = "SELECT * FROM box_office WHERE passenger_id = ? and bus_id = ? "
        + "and id = ?";

    return jdbcTemplate.query(sql, new BoxOfficeRowMapper(), passengerId, busId, boxOfficeId)
        .stream().findFirst().orElse(null);
  }

  public Long createBoxOffice(CreateBoxOfficeRequest request) {

    String sql = "insert into box_office(passenger_id, bus_id, purchase_amount, purchase_date)"
        + " values(?, ?, ?, ?) returning id";

    return jdbcTemplate.queryForObject(sql, Long.class, request.getPassengerId(), request.getBusId(), request.getAmount(), request.getTimeOfPurchase());
  }
}
