package com.example.demo.service;

import com.example.demo.domain.Bus;
import com.example.demo.domain.Passenger;
import com.example.demo.domain.Ticket;
import com.example.demo.domain.create_requests.CreateTicketRequest;
import com.example.demo.repository.BusRepository;
import com.example.demo.repository.PassengerRepository;
import com.example.demo.repository.TicketRepository;
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

  //public Ticket createTicket(CreateTicketRequest request) {
  //  Long ticketId = ticketRepository.createTicket(request);

  //    return ticketRepository
  //      .getTicketPassengerIdAndBusId(request.getPassengerId(), request.getBusId(), ticketId);
  //}

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

    Passenger passenger = passengerRepository.getPassengerId(passengerId);

    Double ticketPrice = busService
        .getTicketPrice(bus.getPricePerKilometre(), bus.getRouteLength());

    Ticket ticket = new Ticket(null, passengerId, busId, ticketPrice, null);

    if(passenger.getMoney() >= ticketPrice && bus.getAmountOfSeats() > 0) {

      bus.setAmountOfSeats(bus.getAmountOfSeats() - 1);
      busRepository.removeSeatFromBus(busId, bus.getAmountOfSeats());

      passenger.setMoney(passenger.getMoney() - ticketPrice);
      passengerRepository.updatePassenger(passengerId, passenger.getMoney());

      ticketRepository.createTicket(ticket);
    } else {
      System.out.println("Passenger doesn't have enough money or not enough seats left in the bus");
    }


    return ticketRepository
          .getTicketPassengerIdAndBusId(ticket.getPassengerId(), ticket.getBusId(), ticket.getId());

  }



}
