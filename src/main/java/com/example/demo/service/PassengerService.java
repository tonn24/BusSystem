package com.example.demo.service;

import com.example.demo.domain.Ticket;
import com.example.demo.domain.create_requests.CreatePassengerRequest;
import com.example.demo.domain.Passenger;
import com.example.demo.repository.PassengerRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

  public void returnMoneyToPassenger(Ticket ticket) {
    BigDecimal amountToBeRepaid = ticket.getAmount().divide(new BigDecimal(2), 1, RoundingMode.CEILING);

    Passenger passenger = passengerRepository.getPassengerId(ticket.getPassengerId());
    passenger.setMoney(passenger.getMoney().add(amountToBeRepaid));
    passengerRepository.updatePassenger(passenger);
  }
}
