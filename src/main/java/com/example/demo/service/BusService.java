package com.example.demo.service;

import com.example.demo.domain.Bus;
import com.example.demo.domain.create_requests.CreateBusRequest;
import com.example.demo.exceptions.BusMissingAttributeException;
import com.example.demo.repository.BusRepository;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
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

  public BigDecimal getTicketPrice(BigDecimal pricePerKilometre, Integer routeLength) {

    if(pricePerKilometre == null || routeLength == null) {
      throw new BusMissingAttributeException();
    }
    return pricePerKilometre.multiply(new BigDecimal(routeLength)).round(new MathContext(3));
  }
}
