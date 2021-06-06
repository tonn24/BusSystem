package com.example.demo.repository.row_mappers;
import com.example.demo.domain.Ticket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import org.springframework.jdbc.core.RowMapper;

public class TicketRowMapper implements RowMapper<Ticket> {

  @Override
  public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {

    Ticket ticket = new Ticket();
    ticket.setId(rs.getLong("id"));
    ticket.setPassengerId(rs.getLong("passenger_id"));
    ticket.setBusId(rs.getLong("bus_id"));
    ticket.setAmount(rs.getBigDecimal("purchase_amount"));

    //TODO Vaja teha DateTime LocalDateTime objektiks
    ticket.setTimeOfPurchase(rs.getDate("purchase_date"));

    return ticket;
  }
}