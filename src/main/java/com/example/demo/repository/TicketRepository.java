package com.example.demo.repository;

import com.example.demo.domain.Ticket;
import com.example.demo.repository.row_mappers.TicketRowMapper;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TicketRepository {

  private final JdbcTemplate jdbcTemplate;

  public Ticket getTicketPassengerIdAndBusId(Long passengerId, Long busId, Long boxOfficeId) {
    String sql = "SELECT * FROM box_office WHERE passenger_id = ? and bus_id = ? "
        + "and id = ?";

    return jdbcTemplate.query(sql, new TicketRowMapper(), passengerId, busId, boxOfficeId)
        .stream().findFirst().orElse(null);
  }

  public Long createTicket(Ticket request) {

    String sql = "insert into box_office(passenger_id, bus_id, purchase_amount, purchase_date)"
        + " values(?, ?, ?, ?) returning id";

    return jdbcTemplate
        .queryForObject(sql, Long.class, request.getPassengerId(), request.getBusId(),
            request.getAmount(), request.getTimeOfPurchase());
  }

  public Ticket getTicketById(Long id) {
    String sql = "select * from box_office where id = ?";

    return jdbcTemplate.query(sql, new TicketRowMapper(), id).stream()
        .findFirst().orElse(null);
  }

  public List<Ticket> getAllTickets() {
    String sql = "select * from box_office";

    return jdbcTemplate.query(sql, new TicketRowMapper());
  }

  public void deleteTicketById(Long id) {
    String sql = "delete from box_office where id = ?";

    jdbcTemplate.update(sql, id);
  }

  public BigDecimal getSales() {
    String sql = "select * from box_office";

    List<Ticket> tickets = jdbcTemplate.query(sql, new TicketRowMapper());

    BigDecimal sales = tickets.stream()
        .map(Ticket::getAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    return sales;
  }


  public BigDecimal getSalesByBus(Long busId) {
    String sql = "select * from box_office where bus_id = ?";

    List<Ticket> ticketsWithSpecificBusId = jdbcTemplate.query(sql, new TicketRowMapper(), busId);

    BigDecimal salesByBus = ticketsWithSpecificBusId.stream()
        .map(Ticket::getAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    return salesByBus;
  }

  public Ticket returnMoneyToPassenger(Long id) {
    String sql = "select * from box_office where id = ?";

    Ticket ticket = jdbcTemplate.query(sql, new TicketRowMapper(), id)
        .stream().findFirst().orElse(null);

    return ticket;
  }
}
