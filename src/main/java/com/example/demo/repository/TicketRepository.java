package com.example.demo.repository;

import com.example.demo.domain.BusSales;
import com.example.demo.domain.Ticket;
import com.example.demo.repository.row_mappers.BusSalesRowMapper;
import com.example.demo.repository.row_mappers.TicketRowMapper;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TicketRepository {

  private final JdbcTemplate jdbcTemplate;

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

  public List<BusSales> getSales() {
    String sql = "select * from box_office";

    return jdbcTemplate.query(sql, new BusSalesRowMapper());
  }

  //TODO on vaja teha eraldi controllerisse funktsioon, et kontrollida funktsiooni korrektsust
  public BusSales getSalesByBus(Long busId) {
    String sql = "select * from box_office where bus_id = ?";

    List<Ticket> ticketsWithSpecificBusId = jdbcTemplate.query(sql, new TicketRowMapper(), busId);

    BusSales busSales = new BusSales();

    busSales.setId(busId);
    busSales.setSales(new ArrayList<>());

    ticketsWithSpecificBusId
        .forEach(ticket -> busSales.addSale(ticket.getAmount().round(new MathContext(5))));

    return busSales;
  }
}