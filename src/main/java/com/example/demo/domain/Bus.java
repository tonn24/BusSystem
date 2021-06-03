package com.example.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bus {

  private Long id;
  private String busNumber;
  private String registryNumber;
  private Integer amountOfSeats;
  private Double pricePerKilometre;
  private Integer routeLength;

}
