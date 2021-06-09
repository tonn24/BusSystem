package com.example.demo.controllers;

import com.example.demo.domain.Bus;
import com.example.demo.domain.create_requests.CreateBusRequest;
import com.example.demo.service.BusService;
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
@RequestMapping("bus")
@RequiredArgsConstructor
public class BusController {

  private final BusService busService;

  @GetMapping(value = "/{id}")
  @ResponseBody
  public Bus getBus(@PathVariable Long id) {
    return busService.findBusById(id);
  }

  @PutMapping("")
  public Bus createBus(@RequestBody CreateBusRequest request) {
    return busService.createBus(request);
  }

  @GetMapping(value = "/")
  public List<Bus> getAllBuses() {
    return busService.findAll();
  }

  @DeleteMapping("/{id}")
  public void deleteBusById(@PathVariable Long id) {
    busService.deleteBus(id);
  }
}