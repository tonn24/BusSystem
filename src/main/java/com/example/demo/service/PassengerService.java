package com.example.demo.service;

import com.example.demo.domain.CreatePassengerRequest;
import com.example.demo.domain.Passenger;
import com.example.demo.repository.PassengerRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PassengerService {

  private final PassengerRepository passengerRepository;


  public Passenger createPassenger(CreatePassengerRequest request) {
    Long busId = passengerRepository.createPassenger(request);

    return passengerRepository.getPassengerId(busId);
  }

  public Passenger getPassengerById(Long id) {

    return passengerRepository.getPassengerId(id);

  }

  public void deletePassengerById(Long id) {
    passengerRepository.deletePassengerById(id);
  }

  public List<Passenger> getAllPassengers() {

    return passengerRepository.getAllPassengers();
  }
}
