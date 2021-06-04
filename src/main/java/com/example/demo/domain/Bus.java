package com.example.demo.domain;


import java.math.BigDecimal;
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
  private BigDecimal pricePerKilometre;
  private Integer routeLength;

}
