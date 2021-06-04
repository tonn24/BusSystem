package com.example.demo.domain.create_requests;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBusRequest {

  private String busNumber;
  private String registryNumber;
  private Integer AmountOfSeats;
  private BigDecimal pricePerKilometre;
  private Integer routeLength;

}
