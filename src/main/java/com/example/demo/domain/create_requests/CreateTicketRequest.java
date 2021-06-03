package com.example.demo.domain.create_requests;

import java.util.Date;
import lombok.Data;

@Data
public class CreateTicketRequest {

  private Long passengerId;
  private Long busId;
  private Double amount;
  private Date timeOfPurchase;

}
