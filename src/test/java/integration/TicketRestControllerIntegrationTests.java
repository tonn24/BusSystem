package integration;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.BusApplication;
import com.example.demo.domain.BusSales;
import com.example.demo.domain.Bus;
import com.example.demo.domain.Passenger;
import com.example.demo.domain.Ticket;
import com.example.demo.service.TicketService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = WebEnvironment.MOCK,
    classes = BusApplication.class)
@AutoConfigureMockMvc
public class TicketRestControllerIntegrationTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private TicketService ticketService;

  private Ticket ticket = new Ticket(1L, 2L, 3L, new BigDecimal(23.0), LocalDateTime.now());

  private Bus bus = new Bus(3L, "18A", "125ASD",
      50, new BigDecimal(0.2), 10, 0);

  private Passenger passenger = new Passenger(2L, "p834tiqhg", new BigDecimal("100.0"));

  @Test
  public void givenTicket_whenGetAllTickets_thenStatus200()
      throws Exception {

    when(ticketService.getTicketById(1L)).thenReturn(ticket);

    mvc.perform(get("/box_office/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(1)));
  }

  @Test
  public void givenTickets_whenGetAllTickets_thenStatus200()
      throws Exception {

    List<Ticket> tickets = new ArrayList<>(
        List.of(ticket, new Ticket(2L, 2L, 3L, new BigDecimal("14.0"), LocalDateTime.now())));

    when(ticketService.getAllTickets()).thenReturn(tickets);

    mvc.perform(get("/box_office/")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()", is(2)));
  }

  @Test
  public void buyTicket_thenStatus200() throws Exception {

    when(ticketService.buyTicket(bus.getId(), passenger.getId())).thenReturn(ticket);
    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.put("/box_office/")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .content(getTicketInJSON(1));
    this.mvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status()
            .isOk()).andExpect(content()
        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.busId", is(3)));

    verify(ticketService)
        .buyTicket(bus.getId(), passenger.getId());
  }

  @Test
  public void deletePassenger_thenStatus200() throws Exception {
    this.mvc.perform(MockMvcRequestBuilders
        .delete("/box_office/{id}", "5")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(ticketService).deleteTicketById(5L);
  }

  private String getTicketInJSON(long id) {
    return "{\"id\": 1,"
        + "        \"passengerId\": 2,"
        + "        \"busId\": 3,"
        + "        \"amount\": 23.0,"
        + "        \"timeOfPurchase\": \"2021-06-04T12:48:16.138306\"}";
  }

  @Test
  public void getAllSales()
      throws Exception {

    when(ticketService.getSales()).thenReturn(new ArrayList<>(List.of(new BusSales(1L,
        new ArrayList<>(List.of(new BigDecimal("10.0"), new BigDecimal("15.0")))))));
    mvc.perform(get("/box_office/getSales/")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].id", is(1)));
  }

  @Test
  public void getSalesByBusId()
      throws Exception {

    when(ticketService.getSalesByBusId(1L)).thenReturn(
        new BusSales(3L, new ArrayList<>(List.of(new BigDecimal("10.0"), new BigDecimal("20.0")))));
    mvc.perform(get("/box_office/getSales/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", is(new BusSales(3L,
            new ArrayList<>(List.of(new BigDecimal("10.0"), new BigDecimal("20.0")))))));
  }
}