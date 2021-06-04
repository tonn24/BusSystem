package com.example.demo.BusServiceTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

import com.example.demo.domain.Bus;
import com.example.demo.service.BusService;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BusServiceTests {

  @Mock
  private BusService busService;

  @Test
  public void getTicketPrice() {

    Bus bus = Mockito.mock(Bus.class);
    when(bus.getPricePerKilometre()).thenReturn(new BigDecimal(0.3));
    when(bus.getRouteLength()).thenReturn(20);

    System.out.println(busService.getTicketPrice(bus.getPricePerKilometre(), bus.getRouteLength()));

  }



}
