package com.example.demo.service;

import com.example.demo.domain.BusSales;
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
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
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

    passenger.setMoney(passenger.getMoney().subtract(ticketPrice));
    passengerRepository.updatePassenger(passenger);

    Ticket ticket = new Ticket(null, passengerId, busId, ticketPrice, LocalDateTime.now());
    ticketRepository.createTicket(ticket);

    return ticketRepository
        .getTicketById(ticket.getId());
  }

  public List<BusSales> getSales() {
    return ticketRepository.getSales();
  }

  public BusSales getSalesByBusId(Long busId) {
    return ticketRepository.getSalesByBus(busId);
  }

  public void returnMoneyToPassenger(Long id) {
    Ticket ticket = ticketRepository.getTicketById(id);

    passengerService.returnMoneyToPassenger(ticket);
  }
}