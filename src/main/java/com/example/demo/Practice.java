package com.example.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.http.converter.json.GsonBuilderUtils;

public class Practice {

  public static void main(String[] args) {
    //Getting the current date
    Date date = new Date();
    //System.out.println("Date is: "+date);

    //Getting the default zone id
    ZoneId defaultZoneId = ZoneId.systemDefault();

    //Converting the date to Instant
    Instant instant = date.toInstant();

    //Converting the Date to LocalDate
    LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
    //System.out.println(date);
    //System.out.println("Local Date is: "+localDate);

    Date dateToConvert = Date.from(Instant.now());

    LocalDateTime date1 = dateToConvert.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    System.out.println(date1);

    System.out.println(dateToConvert);
  }
}
