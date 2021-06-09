package repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.example.demo.domain.Passenger;
import com.example.demo.domain.create_requests.CreatePassengerRequest;
import com.example.demo.repository.PassengerRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PassengerRepositoryIntegrationTest  extends BaseRepositoryIntegrationTest {

  @Autowired
  private PassengerRepository passengerRepository;

  @Test
  public void shouldGetPassengerById() {
    Long passengerId = passengerRepository.createPassenger(new CreatePassengerRequest("qsio2134", new BigDecimal("100.0")));

    assertThat(passengerRepository.getPassengerId(passengerId).getIdCode(), is("qsio2134"));
  }

  @Test
  public void getAllPassengers() {
    Long passengerId = passengerRepository.createPassenger(new CreatePassengerRequest("qsio2134", new BigDecimal("100.0")));

    passengerRepository.getPassengerId(passengerId);

    assertThat(passengerRepository.getAllPassengers().get(0).getIdCode(), is("qsio2134"));
  }

  @Test
  public void deletePassengerById() {
    Long passengerId = passengerRepository.createPassenger(new CreatePassengerRequest("qsio2134", new BigDecimal("100.0")));

    assertThat(passengerRepository.getAllPassengers().size(), is(1));

    passengerRepository.deletePassengerById(passengerId);

    assertThat(passengerRepository.getAllPassengers().size(), is(0));
  }

  @Test
  public void shouldUpdatePassenger() {
    Long passengerId = passengerRepository.createPassenger(new CreatePassengerRequest("qsio2134", new BigDecimal("100.0")));

    Passenger passenger = new Passenger(passengerId, "3813712", new BigDecimal("100.0"));

    passengerRepository.updatePassenger(passenger);

    assertThat(passenger.getIdCode(), is("3813712"));
  }
}
