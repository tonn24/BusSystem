package com.example.demo.service;

import com.example.demo.domain.Bus;
import com.example.demo.domain.Passenger;
import com.example.demo.domain.Ticket;
import com.example.demo.exceptions.BusNotFoundException;
import com.example.demo.exceptions.NotEnoughMoneyException;
import com.example.demo.repository.BusRepository;
import com.example.demo.repository.PassengerRepository;
import com.example.demo.repository.TicketRepository;
import java.math.BigDecimal;
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

    if(bus == null) {
      throw new BusNotFoundException();
    }

    Passenger passenger = passengerRepository.getPassengerId(passengerId);

    if(passenger == null) {
      throw new PassengerNotFoundException();
    }

    BigDecimal ticketPrice = busService
        .getTicketPrice(bus.getPricePerKilometre(), bus.getRouteLength());

    Ticket ticket = new Ticket(null, passengerId, busId, ticketPrice, LocalDateTime.now());

    if(passenger.getMoney().compareTo(ticketPrice) >= 0 && bus.getAmountOfSeats() > 0) {

      bus.setAmountOfSeats(bus.getAmountOfSeats() - 1);
      busRepository.removeSeatFromBus(busId, bus.getAmountOfSeats());

      passenger.setMoney(passenger.getMoney().subtract(ticketPrice));
      passengerRepository.updatePassenger(passengerId, passenger.getMoney());

      ticketRepository.createTicket(ticket);


    } else {
      throw new NotEnoughMoneyException();
    }


    return ticketRepository
          .getTicketPassengerIdAndBusId(ticket.getPassengerId(), ticket.getBusId(), ticket.getId());
  }

  private BigDecimal getSales() {
    return ticketRepository.getSales();
  }

  private BigDecimal getSalesByBus(Long busId) {
    return ticketRepository.getSalesByBus(busId);
  }

  public void returnMoneyToPassenger(Long id) {
    Ticket ticket = ticketRepository.returnMoneyToPassenger(id);

    passengerService.returnMoneyToPassenger(ticket);
  }


}
