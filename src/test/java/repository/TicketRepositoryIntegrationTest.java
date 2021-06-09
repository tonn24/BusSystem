package repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.example.demo.domain.Ticket;
import com.example.demo.domain.create_requests.CreateBusRequest;
import com.example.demo.domain.create_requests.CreatePassengerRequest;
import com.example.demo.repository.BusRepository;
import com.example.demo.repository.PassengerRepository;
import com.example.demo.repository.TicketRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TicketRepositoryIntegrationTest extends BaseRepositoryIntegrationTest{

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private PassengerRepository passengerRepository;

  @Autowired
  private BusRepository busRepository;

  private final CreateBusRequest createBusRequest = new CreateBusRequest("28", "AAA123", 50, new BigDecimal("0.2"), 15, 15);

  private CreatePassengerRequest createPassengerRequest = new CreatePassengerRequest("qsio2134", new BigDecimal("100.0"));


  @Test
  public void shouldGetTicketById() {
    Long passengerId = passengerRepository.createPassenger(createPassengerRequest);

    Long busId = busRepository.createBus(createBusRequest);

    Ticket ticket = new Ticket(1L, passengerId, busId, new BigDecimal("25.0"), LocalDateTime.now());

    Long ticketId = ticketRepository.createTicket(ticket);

    assertThat(ticketRepository.getTicketById(ticketId).getBusId(), is(busId));
  }

  @Test
  public void shouldGetAllTickets() {
    Long passengerId = passengerRepository.createPassenger(new CreatePassengerRequest("qsio2134", new BigDecimal("100.0")));

    Long busId = busRepository.createBus(createBusRequest);

    Ticket ticket = new Ticket(1L, passengerId, busId, new BigDecimal("25.0"), LocalDateTime.now());

    ticketRepository.createTicket(ticket);

    assertThat(ticketRepository.getAllTickets().get(0).getBusId(), is(busId));
  }

  @Test
  public void shouldDeleteTicketById() {
    Long passengerId = passengerRepository.createPassenger(new CreatePassengerRequest("qsio2134", new BigDecimal("100.0")));

    Long busId = busRepository.createBus(createBusRequest);

    Ticket ticket = new Ticket(1L, passengerId, busId, new BigDecimal("25.0"), LocalDateTime.now());

    Long ticketId = ticketRepository.createTicket(ticket);

    assertThat(ticketRepository.getAllTickets().size(), is(1));

    ticketRepository.deleteTicketById(ticketId);

    assertThat(ticketRepository.getAllTickets().size(), is(0));
  }

  @Test
  public void shouldAllGetSales() {
    Long passengerId = passengerRepository.createPassenger(new CreatePassengerRequest("qsio2134", new BigDecimal("100.0")));

    Long busId = busRepository.createBus(createBusRequest);

    Ticket ticket = new Ticket(1L, passengerId, busId, new BigDecimal(25.0), LocalDateTime.now());

    ticketRepository.createTicket(ticket);

    assertThat(ticketRepository.getSales().get(0).getSales(), is(List.of(new BigDecimal(25.0))));
  }

  @Test
  public void shouldGetSalesByBusId() {
    Long passengerId = passengerRepository.createPassenger(new CreatePassengerRequest("qsio2134", new BigDecimal("100.0")));

    Long busId = busRepository.createBus(createBusRequest);

    Ticket ticket = new Ticket(1L, passengerId, busId, new BigDecimal(25.0), LocalDateTime.now());

    Long ticketId = ticketRepository.createTicket(ticket);

    assertThat(ticketRepository.getSalesByBus(ticketId).getSales(), is(List.of(new BigDecimal(25.0))));
  }
}
