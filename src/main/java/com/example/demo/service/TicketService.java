package com.example.demo.service;

import com.example.demo.domain.Bus;
import com.example.demo.domain.Passenger;
import com.example.demo.domain.Ticket;
import com.example.demo.exceptions.BusNotFoundException;
import com.example.demo.exceptions.NotEnoughMoneyException;
import com.example.demo.exceptions.NotEnoughSeatsException;
import com.example.demo.repository.BusRepository;
import com.example.demo.repository.PassengerRepository;
import com.example.demo.repository.TicketRepository;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

  private final TicketRepository ticketRepository;
  private final BusRepository busRepository;
  private final PassengerRepository passengerRepository;
  private final BusService busService;
  private final PassengerService passengerService;

  public Ticket getTicketById(Long id) {
    return ticketRepository.getTicketById(id);
  }

  public List<Ticket> getAllTickets() {
    return ticketRepository.getAllTickets();
  }

  public void deleteTicketById(Long id) {
    ticketRepository.deleteTicketById(id);
  }

  public Ticket buyTicket(Long busId, Long passengerId) {

    Bus bus = busRepository.getBusById(busId);

    if (bus == null) {
      throw new BusNotFoundException();
    }

    Passenger passenger = passengerRepository.getPassengerId(passengerId);

    if (passenger == null) {
      throw new PassengerNotFoundException();
    }

    BigDecimal ticketPrice = busService
        .getTicketPrice(bus.getPricePerKilometre(), bus.getRouteLength());

    if (passenger.getMoney().compareTo(ticketPrice) <= 0) {
      throw new NotEnoughMoneyException();

    } else if (bus.getAmountOfSeats() <= 0) {
      throw new NotEnoughSeatsException();
    }

    bus.setAmountOfSeats(bus.getAmountOfSeats() - 1);
    busRepository.removeSeatFromBus(busId, bus.getAmountOfSeats());

    passenger.setMoney(passenger.getMoney().subtract(ticketPrice));
    passengerRepository.updatePassenger(passenger);

    Ticket ticket = new Ticket(null, passengerId, busId, ticketPrice, Date.from(Instant.now()));
    ticketRepository.createTicket(ticket);

    return ticketRepository
        .getTicketPassengerIdAndBusId(ticket.getPassengerId(), ticket.getBusId(), ticket.getId());
  }

  //TODO controllerisse
  public BigDecimal getSales() {
    return ticketRepository.getSales().round(new MathContext(6));
  }

  public BigDecimal getSalesByBusId(Long busId) {
    return ticketRepository.getSalesByBus(busId).round(new MathContext(5));
  }

  public void returnMoneyToPassenger(Long id) {
    Ticket ticket = ticketRepository.getTicketById(id);

    passengerService.returnMoneyToPassenger(ticket);
  }
}