package com.example.demo.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxOffice {

  private Long id;
  private Long userId;
  private Double amount;
  private Date timeOfPurchase;

}
