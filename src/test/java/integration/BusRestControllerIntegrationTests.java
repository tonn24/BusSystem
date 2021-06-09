package integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.BusApplication;
import com.example.demo.domain.Bus;
import com.example.demo.domain.create_requests.CreateBusRequest;
import com.example.demo.service.BusService;
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

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = WebEnvironment.MOCK,
    classes = BusApplication.class)
@AutoConfigureMockMvc
public class BusRestControllerIntegrationTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private BusService busService;

  private Bus bus = new Bus(1L, "18A", "125ASD", 50, new BigDecimal(0.2), 10, 0);

  @Test
  public void shouldGetBusById()
      throws Exception {

    when(busService.findBusById(1L)).thenReturn(bus);

    mvc.perform(get("/bus/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(1)));
  }

  @Test
  public void shouldGivenBuses()
      throws Exception {

    List<Bus> buses = new ArrayList<>(List.of(bus, new Bus(2L, "126", "121BSD", 34,
        new BigDecimal(0.23), 10, 0)));

    when(busService.findAll()).thenReturn(buses);

    mvc.perform(get("/bus/")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()", is(2)));
  }

  @Test
  public void shouldCreateBus() throws Exception {

    when(busService.createBus(any(CreateBusRequest.class))).thenReturn(bus);
    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.put("/bus/")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .content(getBusInJson(1));
    this.mvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status()
            .isOk()).andExpect(content()
        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.registryNumber", is("125ASD")));

    verify(busService)
        .createBus(new CreateBusRequest("18A", "125ASD", 50, new BigDecimal("0.2"), 10, 0));
  }

  @Test
  public void shouldDeletePassenger() throws Exception {
    this.mvc.perform(MockMvcRequestBuilders
        .delete("/bus/{id}", "5")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(busService).deleteBus(5L);
  }

  private String getBusInJson(long id) {
    return "{\"id\": 9,"
        + "        \"busNumber\": \"18A\","
        + "        \"registryNumber\": \"125ASD\","
        + "        \"amountOfSeats\": 50,\n"
        + "        \"pricePerKilometre\": 0.2,"
        + "        \"routeLength\": 10,"
        + "        \"freeAmountOfSeats\": 0}";
  }
}
