package com.example.demo.repository;

import com.example.demo.domain.Bus;
import com.example.demo.domain.create_requests.CreateBusRequest;
import com.example.demo.repository.row_mappers.BusRowMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BusRepository {

  private final JdbcTemplate jdbcTemplate;

  public Bus getBusById(Long id) {
    String sql = "SELECT * FROM bus WHERE ID = ?";

    return jdbcTemplate.query(sql, new BusRowMapper(), id).stream()
        .findFirst().orElse(null);
  }

  public Long createBus(CreateBusRequest request) {
    String sql =
        "insert into bus(bus_number, registry_number, amount_of_seats, price_per_kilometre,  route_length)"
            + " values(?, ?, ?, ?, ?) returning id";

    return jdbcTemplate
        .queryForObject(sql, Long.class, request.getBusNumber(), request.getRegistryNumber(),
            request.getAmountOfSeats(), request.getPricePerKilometre(), request.getRouteLength());
  }

  public List<Bus> findAllBuses() {
    String sql = "select * from bus";

    return jdbcTemplate.query(
        sql,
        new BusRowMapper());
  }

  public void deleteBus(Long id) {
    String sql = "delete from bus where id = ?";

    jdbcTemplate.update(sql, id);

    System.out.println("Bus with an id of " + id + " is deleted");
  }

  public void removeSeatFromBus(Long busId, Integer amountOfSeats) {
    String sql = "update bus set amount_of_seats = ?  where id = ?";

    jdbcTemplate.update(sql, amountOfSeats, busId);
  }
}
