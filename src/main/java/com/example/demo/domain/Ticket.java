package com.example.demo.domain;

import java.sql.Timestamp;
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
  private Double amount;
  private Timestamp timeOfPurchase;

}
