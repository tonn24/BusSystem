package com.example.demo.controllers;

import com.example.demo.domain.BoxOffice;
import com.example.demo.domain.CreateBoxOfficeRequest;
import com.example.demo.service.BoxOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("box_office")
public class BoxOfficeController {

  private final BoxOfficeService boxOfficeService;



  @PutMapping("")
  public BoxOffice createBoxOffice(@RequestBody CreateBoxOfficeRequest request) {
    return boxOfficeService.createPostOffice(request);
  }

}
