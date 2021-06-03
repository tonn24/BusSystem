package com.example.demo.service;

import com.example.demo.domain.Bus;
import com.example.demo.domain.create_requests.CreateBusRequest;
import com.example.demo.repository.BusRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BusService {

  private final BusRepository busRepository;

  public Bus findBusById(Long id) {
    return busRepository.getBusById(id);
  }

  public Bus createBus(CreateBusRequest request) {
    Long busId = busRepository.createBus(request);

    return busRepository.getBusById(busId);
  }


  public List<Bus> findAll() {
    return busRepository.findAllBuses();
  }

  public void deleteBus(Long id) {
    busRepository.deleteBus(id);
  }

  public Double getTicketPrice(Double pricePerKilometre, Integer routeLength) {
    return pricePerKilometre * routeLength;
  }
}
