package com.example.demo.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.domain.Passenger;
import com.example.demo.domain.create_requests.CreatePassengerRequest;
import com.example.demo.repository.PassengerRepository;
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
  Passenger passenger;

  @Mock
  List<Passenger> passengers;

  @Mock
  PassengerRepository passengerRepository;

  @InjectMocks
  PassengerService passengerService;

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

  @Test//TODO Tee test korda
  public void shouldReturnMoneyToPassengers() {

  }
}
