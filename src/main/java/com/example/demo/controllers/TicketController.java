package com.example.demo.controllers;
import com.example.demo.domain.BusSales;
import com.example.demo.domain.Ticket;
import com.example.demo.service.TicketService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("box_office")
public class TicketController {

  private final TicketService ticketService;

  @GetMapping("/{id}")
  @ResponseBody
  public Ticket getTicketById(@PathVariable Long id) {
    return ticketService.getTicketById(id);
  }

  @PutMapping("")
  public Ticket buyTicket(@RequestBody Ticket request) {
    return ticketService.buyTicket(request.getBusId(), request.getPassengerId());
  }

  @GetMapping("/")
  @ResponseBody
  public List<Ticket> getAllTickets() {
    return ticketService.getAllTickets();
  }

  @DeleteMapping("/{id}")
  public void deleteTicketById(@PathVariable Long id) {
    ticketService.returnMoneyToPassenger(id);
    ticketService.deleteTicketById(id);
  }

  @GetMapping("/getSales/")
  public List<BusSales> getSales() {
    return ticketService.getSales();
  }

  @GetMapping("/getSales/{id}")
  public BusSales getSalesByBusId(@PathVariable Long id) {
    return ticketService.getSalesByBusId(id);
  }
}