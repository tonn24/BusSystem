package com.example.demo.service;

import com.example.demo.domain.BoxOffice;
import com.example.demo.domain.CreateBoxOfficeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class BoxOfficeService {

  private final BoxOfficeRepository boxOfficeRepository;

  public BoxOffice createPostOffice(CreateBoxOfficeRequest request) {
    Long boxOfficeId = boxOfficeRepository.createBoxOffice(request);

    return boxOfficeRepository.getBoxOfficeByPassengerIdAndBusId(request.getPassengerId(), request.getBusId(), boxOfficeId);
  }
}
