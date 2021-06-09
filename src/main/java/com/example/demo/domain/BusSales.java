package com.example.demo.domain;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusSales {

  private Long id;
  private List<BigDecimal> sales;

  public void addSale(BigDecimal sale) {
    if(sale != null) {
      sale.round(new MathContext(5));
      sales.add(sale);
    }
  }
}