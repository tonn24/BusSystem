package com.example.demo.repository.rowMappers;
import com.example.demo.domain.BoxOffice;
import com.example.demo.domain.Passenger;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class BoxOfficeRowMapper implements RowMapper<BoxOffice> {

  @Override
  public BoxOffice mapRow(ResultSet rs, int rowNum) throws SQLException {

    BoxOffice boxOffice = new BoxOffice();
    boxOffice.setId(rs.getLong("id"));
    boxOffice.setPassengerId(rs.getLong("passenger_id"));
    boxOffice.setBusId(rs.getLong("bus_id"));
    boxOffice.setAmount(rs.getDouble("purchase_amount"));
    boxOffice.setTimeOfPurchase(rs.getTimestamp("purchase_date"));

    return boxOffice;
  }

}
