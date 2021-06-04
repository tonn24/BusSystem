package com.example.demo.TicketServiceTests;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.example.demo.domain.Bus;
import com.example.demo.domain.Passenger;
import com.example.demo.domain.Ticket;
import com.example.demo.exceptions.BusNotFoundException;
import com.example.demo.repository.BusRepository;
import com.example.demo.repository.PassengerRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.BusService;
import com.example.demo.exceptions.NotEnoughMoneyException;
import com.example.demo.service.PassengerNotFoundException;
import com.example.demo.service.PassengerService;
import com.example.demo.service.TicketService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {

  private static final Long busId = 1L;
  private static final Long passengerId = 2L;

  @Mock
  private BusService busService;

  @Mock
  private PassengerService passengerService;

  @Mock
  BusRepository busRepository;

  @Mock
  PassengerRepository passengerRepository;

  @InjectMocks
  TicketService ticketService;

  @Mock
  TicketRepository ticketRepository;

  @Test
  public void getTicketById() {
    Ticket ticket = new Ticket(5L, passengerId, busId, new BigDecimal(2), LocalDateTime.now());

    assert(ticket.getId().equals(5L));
  }

  @Test(expected = NotEnoughMoneyException.class)
  public void passengerHasNotEnoughMoneyWhenBuyingTicket() {

    Bus bus = new Bus(busId, "asd345", "asd34", 50, new BigDecimal(0.2), 10);
    Passenger passenger = mock(Passenger.class);

    when(busService.getTicketPrice(any(), any())).thenReturn(new BigDecimal(25.0));

    when(busRepository.getBusById(busId)).thenReturn(bus);

    when(passenger.getMoney()).thenReturn(new BigDecimal(5));

    when(passengerRepository.getPassengerId(passengerId)).thenReturn(passenger);

    ticketService.buyTicket(busId, passengerId);

    verify(ticketRepository).createTicket(any());
  }

  @Test
  public void passengerHasEnoughMoneyWhenBuyingTicket() {
    Bus bus = new Bus(busId, "asd345", "asd34", 50, new BigDecimal(0.2), 10);

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

    Bus bus = new Bus(busId, "asd345", "asd34", 50, new BigDecimal(0.2), 10);

    ticketService.buyTicket(bus.getId(), passenger.getId());
  }

  @Test(expected = PassengerNotFoundException.class)
  public void shouldReturnPassengerNotFoundTest() {
    Passenger passenger = mock(Passenger.class);

    Bus bus = new Bus(busId, "asd345", "asd34", 50, new BigDecimal(0.2), 10);

    when(busRepository.getBusById(1L)).thenReturn(bus);

    when(passenger.getId()).thenReturn(2L);

    ticketService.buyTicket(bus.getId(), passenger.getId());
  }
}
