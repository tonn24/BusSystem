package com.example.demo.domain.create_requests;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePassengerRequest {

  private String idCode;
  private BigDecimal money;
}