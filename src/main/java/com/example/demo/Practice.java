package com.example.demo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Practice {

  public static void main(String[] args) {
    Date currentDate = new Date();
    currentDate.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    System.out.println(currentDate);
    LocalDateTime localDateTime = Instant
        .ofEpochMilli(currentDate.getTime())
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    System.out.println("Locale date time is :" + localDateTime);

    LocalDateTime localDateTime2 = currentDate
        .toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    System.out.println("Locale date time is Options 2:" + localDateTime2);
  }
}
