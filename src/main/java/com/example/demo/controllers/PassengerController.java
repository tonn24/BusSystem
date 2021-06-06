package com.example.demo.controllers;
import com.example.demo.domain.create_requests.CreatePassengerRequest;
import com.example.demo.domain.Passenger;
import com.example.demo.service.PassengerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

  @GetMapping(value = "/{id}")
  @ResponseBody
  public Passenger getPassenger(@PathVariable Long id) {
    return passengerService.getPassengerById(id);
  }

  @GetMapping("/")
  public List<Passenger> getAllPassengers() {
    return passengerService.getAllPassengers();
  }

  @DeleteMapping("/{id}")
  public void deletePassengerById(@PathVariable Long id) {
    passengerService.deletePassengerById(id);
  }
}
