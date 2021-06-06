package com.example.demo.service;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.example.demo.domain.Bus;
import com.example.demo.domain.Passenger;
import com.example.demo.domain.Ticket;
import com.example.demo.exceptions.BusNotFoundException;
import com.example.demo.exceptions.NotEnoughSeatsException;
import com.example.demo.repository.BusRepository;
import com.example.demo.repository.PassengerRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.exceptions.NotEnoughMoneyException;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTests {

  private static final Long busId = 1L;
  private static final Long passengerId = 2L;

  @Mock
  private BusService busService;

  @Mock
  BusRepository busRepository;

  @Mock
  PassengerRepository passengerRepository;

  @InjectMocks
  TicketService ticketService;

  @Mock
  TicketRepository ticketRepository;

  @Mock
  Ticket ticket;

  @Mock
  Passenger passenger;

  @Mock
  List<Ticket> tickets;

  private final Bus bus = new Bus(busId, "asd345", "asd34", 50, new BigDecimal(0.2), 10);

  @Test
  public void getTicketByIdMovesToTicketRepository() {
    when(ticketRepository.getTicketById(2L)).thenReturn(ticket);

    ticketService.getTicketById(2L);

    verify(ticketRepository).getTicketById(2L);
  }

  @Test(expected = NotEnoughMoneyException.class)
  public void passengerHasNotEnoughMoneyWhenBuyingTicket() {

    when(busService.getTicketPrice(any(), any())).thenReturn(new BigDecimal(25.0));

    when(busRepository.getBusById(busId)).thenReturn(bus);

    when(passenger.getMoney()).thenReturn(new BigDecimal(5));

    when(passengerRepository.getPassengerId(passengerId)).thenReturn(passenger);

    ticketService.buyTicket(busId, passengerId);

    verify(ticketRepository).createTicket(any());
  }

  @Test(expected = NotEnoughSeatsException.class)
  public void notEnoughSeatsLeftInBus() {

    bus.setAmountOfSeats(0);

    when(busService.getTicketPrice(any(), any())).thenReturn(new BigDecimal(25.0));

    when(busRepository.getBusById(busId)).thenReturn(bus);

    when(passenger.getMoney()).thenReturn(new BigDecimal(500));

    when(passengerRepository.getPassengerId(passengerId)).thenReturn(passenger);

    ticketService.buyTicket(busId, passengerId);

    verify(ticketRepository).createTicket(any());
  }

  @Test
  public void passengerHasEnoughMoneyWhenBuyingTicket() {

    Passenger passenger = new Passenger(1, "234235318", new BigDecimal(100.0));

    when(busService.getTicketPrice(any(), any())).thenReturn(new BigDecimal(25.0));

    when(busRepository.getBusById(busId)).thenReturn(bus);

    when(passengerRepository.getPassengerId(passengerId)).thenReturn(passenger);

    ticketService.buyTicket(busId, passengerId);

    assert(passenger.getMoney().equals(new BigDecimal(75)));
  }

  @Test(expected = BusNotFoundException.class)
  public void shouldReturnBusNotFoundTest() {
    Passenger passenger = new Passenger(2L, "390210934", new BigDecimal(100.0));

    ticketService.buyTicket(bus.getId(), passenger.getId());
  }

  @Test(expected = PassengerNotFoundException.class)
  public void shouldReturnPassengerNotFoundTest() {

    when(busRepository.getBusById(1L)).thenReturn(bus);

    when(passenger.getId()).thenReturn(2L);

    ticketService.buyTicket(bus.getId(), passenger.getId());
  }

  @Test
  public void shouldGetTicketById() {
    when(ticketRepository.getTicketById(1L)).thenReturn(ticket);

    ticketService.getTicketById(1L);

    verify(ticketRepository).getTicketById(1L);
  }

  @Test
  public void shouldGetAllTickets() {
    when(ticketRepository.getAllTickets()).thenReturn(tickets);

    ticketService.getAllTickets();

    verify(ticketRepository).getAllTickets();
  }

  //TODO
  @Test
  public void shouldReturnMoneyToPassenger() {
    //when(ticketRepository.getTicketById(1L)).thenReturn(ticket);
  }

  @Test
  public void shouldReturnGetSales() {
    when(ticketRepository.getSales()).thenReturn(new BigDecimal("129"));

    ticketService.getSales();

    verify(ticketRepository).getSales();
  }

  @Test
  public void getSalesByBusId() {
    when(ticketRepository.getSalesByBus(busId)).thenReturn(new BigDecimal(6.0));

    ticketService.getSalesByBusId(busId);

    verify(ticketRepository).getSalesByBus(busId);
  }
}
