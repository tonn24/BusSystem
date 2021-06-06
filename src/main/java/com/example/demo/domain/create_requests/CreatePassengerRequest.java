package com.example.demo.domain.create_requests;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreatePassengerRequest {

  private String idCode;
  private BigDecimal money;
}