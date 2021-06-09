package com.example.demo.domain.create_requests;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBusRequest {

  private String busNumber;
  private String registryNumber;
  private Integer amountOfSeats;
  private BigDecimal pricePerKilometre;
  private Integer routeLength;
  private Integer freeAmountOfSeats;
}