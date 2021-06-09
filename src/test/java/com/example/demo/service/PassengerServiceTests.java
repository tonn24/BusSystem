package com.example.demo.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.domain.Passenger;
import com.example.demo.domain.Ticket;
import com.example.demo.domain.create_requests.CreatePassengerRequest;
import com.example.demo.repository.PassengerRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PassengerServiceTests {

  private static final Long passengerId = 2L;

  @Mock
  private Passenger passenger;

  @Mock
  private List<Passenger> passengers;

  @Mock
  private PassengerRepository passengerRepository;

  @InjectMocks
  private PassengerService passengerService;

  @Test
  public void shouldGetPassengerById() {
    when(passengerRepository.getPassengerId(passengerId)).thenReturn(passenger);

    passengerService.getPassengerById(passengerId);

    verify(passengerRepository).getPassengerId(passengerId);
  }

  @Test
  public void shouldGetAllPassengers() {
    when(passengerRepository.getAllPassengers()).thenReturn(passengers);

    passengerService.getAllPassengers();

    verify(passengerRepository).getAllPassengers();
  }

  @Test
  public void shouldCreatePassenger() {
    CreatePassengerRequest request = mock(CreatePassengerRequest.class);

    passengerService.createPassenger(request);

    verify(passengerRepository).createPassenger(request);
  }

  @Test//TODO Tee test korda.
  public void shouldReturnMoneyToPassengers() {
    Ticket ticket1 = new Ticket(1L, 1L, 2L, new BigDecimal("25.0"), LocalDateTime.now());

    Passenger passenger1 = new Passenger(ticket1.getPassengerId(), "asgu2344", new BigDecimal("100.0"));

    BigDecimal amountToBeRepaid = ticket1.getAmount().divide(new BigDecimal(2), 3, RoundingMode.CEILING);

    passenger1.setMoney(passenger1.getMoney().add(amountToBeRepaid));

    when(passengerRepository.getPassengerId(passenger1.getId())).thenReturn(passenger1);

    passengerService.returnMoneyToPassenger(ticket1);

    verify(passengerRepository).updatePassenger(passenger1);
  }

  @Test
  public void deletePassengerById() {
    passengerService.deletePassengerById(passengerId);

    verify(passengerRepository).deletePassengerById(passengerId);
  }
}
