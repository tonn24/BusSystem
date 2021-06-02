package com.example.demo.service;

import com.example.demo.domain.CreatePassengerRequest;
import com.example.demo.domain.Passenger;
import com.example.demo.repository.PassengerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@AllArgsConstructor
public class PassengerService {

  private final PassengerRepository passengerRepository;


  public Passenger createPassenger(CreatePassengerRequest request) {
    Long busId = passengerRepository.createPassenger(request);

    return passengerRepository.getPassengerId(busId);
  }
}
