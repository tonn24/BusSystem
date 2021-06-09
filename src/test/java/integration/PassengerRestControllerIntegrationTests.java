package integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.BusApplication;
import com.example.demo.domain.Passenger;
import com.example.demo.domain.create_requests.CreatePassengerRequest;
import com.example.demo.repository.PassengerRepository;
import com.example.demo.service.PassengerService;
import java.math.BigDecimal;
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

import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = WebEnvironment.MOCK,
    classes = BusApplication.class)
@AutoConfigureMockMvc
public class PassengerRestControllerIntegrationTests {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private PassengerRepository repository;

  @MockBean
  private PassengerService passengerService;

  @Test
  public void givenPassenger_whenGetPassengers_thenStatus200()
      throws Exception {

    Passenger passenger = new Passenger(1L, "p834tiqhg", new BigDecimal(100.0));
    when(passengerService.getPassengerById(1L)).thenReturn(passenger);

    mvc.perform(get("/passenger/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.idCode", is("p834tiqhg")));
  }

  @Test
  public void givenPassengers_whenGetPassengers_thenStatus200()
      throws Exception {

    Passenger passenger = new Passenger(1L, "p834tiqhg", new BigDecimal("100.0"));
    List<Passenger> passengers = new ArrayList<>(
        List.of(passenger, new Passenger(1L, "3901219034", new BigDecimal("12.0"))));

    when(passengerService.getAllPassengers()).thenReturn(passengers);

    mvc.perform(get("/passenger/")
        .contentType(MediaType.APPLICATION_JSON))

        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()", is(2)));
  }

  @Test
  public void createPassenger_thenStatus200() throws Exception {
    Passenger passenger = new Passenger(1L, "p834tiqhg", new BigDecimal("100.0"));

    when(passengerService.createPassenger(any(CreatePassengerRequest.class))).thenReturn(passenger);
    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.put("/passenger/")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .content(getPassengerInJson(1));
    this.mvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status()
            .isOk()).andExpect(content()
        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.idCode", is("p834tiqhg")));
    ;
    verify(passengerService)
        .createPassenger(new CreatePassengerRequest("39012349908", new BigDecimal(100)));
  }

  @Test
  public void deletePassenger_thenStatus200() throws Exception {
    this.mvc.perform(MockMvcRequestBuilders
        .delete("/passenger/{id}", "5")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(passengerService).deletePassengerById(5L);
  }

  private String getPassengerInJson(long id) {
    return "{\"idCode\": \"39012349908\","
        + "    \"money\": 100"
        + "}";
  }
}
