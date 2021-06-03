package com.example.demo.domain.create_requests;

import lombok.Data;

@Data
public class CreateBusRequest {

  private String busNumber;
  private String registryNumber;
  private Integer AmountOfSeats;
  private Double pricePerKilometre;
  private Integer routeLength;

}
