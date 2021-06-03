package com.example.demo.domain;

import java.util.Date;
import lombok.Data;

@Data
public class CreateBoxOfficeRequest {

  private Long passengerId;
  private Long busId;
  private Double amount;
  private Date timeOfPurchase;

}
