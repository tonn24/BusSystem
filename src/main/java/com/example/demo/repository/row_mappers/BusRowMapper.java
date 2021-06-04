package com.example.demo.repository.row_mappers;

import com.example.demo.domain.Bus;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class BusRowMapper implements RowMapper<Bus> {

  @Override
  public Bus mapRow(ResultSet rs, int rowNum) throws SQLException {

    Bus bus = new Bus();
    bus.setId(rs.getLong("id"));
    bus.setBusNumber(rs.getString("bus_number"));
    bus.setRegistryNumber(rs.getString("registry_number"));
    bus.setAmountOfSeats(rs.getInt("amount_of_seats"));
    bus.setPricePerKilometre(rs.getBigDecimal("price_per_kilometre"));
    bus.setRouteLength(rs.getInt("route_length"));

    return bus;
    }

  }