package com.example.demo.domain.create_requests;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTicketRequest {

  private Long passengerId;
  private Long busId;
  private BigDecimal amount;
  private Date timeOfPurchase;
}