package com.example.demo.domain;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

  private Long id;
  private Long passengerId;
  private Long busId;
  private BigDecimal amount;
  //TODO: Vaja teha LocalDateTime-ks
  private Date timeOfPurchase;
}