package com.example.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Practice {

  public static void main(String[] args) {

    BigDecimal  ten = new BigDecimal(10);

    System.out.println(ten.divide(new BigDecimal(2), 1, RoundingMode.CEILING));
  }

}
