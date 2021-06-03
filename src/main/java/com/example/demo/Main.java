package com.example.demo;

import com.example.demo.domain.Bus;
import com.example.demo.domain.Passenger;

public class Main {

  public static void main(String[] args) {
    Bus bus = new Bus();
    bus.setRouteLength(10);
    bus.setPricePerKilometre(0.18);

    Passenger passenger = new Passenger();
    passenger.setId(2);
    passenger.setMoney(100.0);
  }

}
