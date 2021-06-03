package com.example.demo.repository.rowMappers;
import com.example.demo.domain.Passenger;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PassengerRowMapper implements RowMapper<Passenger> {

  @Override
  public Passenger mapRow(ResultSet rs, int rowNum) throws SQLException {

    Passenger passenger = new Passenger();
    passenger.setId(rs.getLong("id"));
    passenger.setIdCode(rs.getString("id_code"));
    passenger.setMoney(rs.getDouble("free_money"));


    return passenger;
  }

}
