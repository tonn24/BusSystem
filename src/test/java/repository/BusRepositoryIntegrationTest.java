package repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.example.demo.domain.create_requests.CreateBusRequest;
import com.example.demo.repository.BusRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BusRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

  @Autowired
  private BusRepository busRepository;

  @Test
  public void shouldGetBusById() {

    CreateBusRequest createBusRequest = new CreateBusRequest("28", "AAA123", 50, new BigDecimal("0.2"), 15, 15);

    Long busId = busRepository
        .createBus(createBusRequest);

    assertThat(busRepository.getBusById(busId).getRegistryNumber(), is("AAA123"));
  }

  @Test
  public void shouldGetAllBusesById() {
    CreateBusRequest createBusRequest = new CreateBusRequest("28", "AAA123", 50, new BigDecimal("0.2"), 15, 15);

    busRepository.createBus(createBusRequest);

    assertThat(busRepository.findAllBuses().get(0).getRegistryNumber(), is("AAA123"));
  }


  @Test
  public void deleteBusById() {

    CreateBusRequest createBusRequest = new CreateBusRequest("28", "AAA123", 50, new BigDecimal("0.2"), 15, 15);

    Long busId = busRepository.createBus(createBusRequest);

    busRepository.getBusById(busId);

    assertThat(busRepository.findAllBuses().size(), is(1));

    busRepository.deleteBus(busId);

    assertThat(busRepository.findAllBuses().size(), is(0));
  }
}
