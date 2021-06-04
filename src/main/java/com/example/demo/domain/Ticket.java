package com.example.demo.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
  private LocalDateTime timeOfPurchase;

}
