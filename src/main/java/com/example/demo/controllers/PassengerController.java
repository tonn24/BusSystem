package com.example.demo.controllers;

import com.example.demo.domain.CreatePassengerRequest;
import com.example.demo.domain.Passenger;
import com.example.demo.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("passenger")
@RequiredArgsConstructor
public class PassengerController {

  private final PassengerService passengerService;

  @PutMapping("")
  public Passenger createPassenger(@RequestBody CreatePassengerRequest request) {
    return passengerService.createPassenger(request);
  }
}
