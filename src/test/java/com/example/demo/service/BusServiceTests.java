package com.example.demo.service;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.domain.Bus;
import com.example.demo.domain.create_requests.CreateBusRequest;
import com.example.demo.exceptions.BusMissingAttributeException;
import com.example.demo.repository.BusRepository;
import java.math.BigDecimal;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BusServiceTests {

  @InjectMocks
  BusService busService;

  @Mock
  BusRepository busRepository;

  @Mock
  Bus bus;

  @Mock
  List<Bus> buses;

  @Test
  public void getTicketPrice() {

    when(bus.getPricePerKilometre()).thenReturn(new BigDecimal(0.3));
    when(bus.getRouteLength()).thenReturn(20);

    MatcherAssert
        .assertThat(busService.getTicketPrice(bus.getPricePerKilometre(), bus.getRouteLength()),
            is(new BigDecimal("6.00")));
  }

  @Test(expected = BusMissingAttributeException.class)
  public void busMissingAttributesInGetTicketPrice() {

    Bus bus = mock(Bus.class);

    busService.getTicketPrice(bus.getPricePerKilometre(), bus.getRouteLength());
  }

  @Test
  // TODO Kas test peaks olema lõpus või jääbki lihtsalt shouldFindBusById?
  public void shouldFindBusByIdTest() {
    when(busRepository.getBusById(1L)).thenReturn(bus);

    busService.findBusById(1L);

    verify(busRepository).getBusById(1L);
  }

  @Test //TODO: Test on vaja lõpetada
  public void deleteBusMovesToBusRepositoryTest() {

  }

  @Test
  //TODO Või hoopis should create bus?
  public void createBusShouldMoveToBusRepositoryTest() {
    CreateBusRequest request = mock(CreateBusRequest.class);

    busService.createBus(request);

    verify(busRepository).createBus(request);
  }

  @Test
  //TODO või findAllShouldMoveToBusRepository?
  public void shouldFindAllBuses() {
    when(busRepository.findAllBuses()).thenReturn(buses);

    busService.findAll();

    verify(busRepository).findAllBuses();
  }
}
