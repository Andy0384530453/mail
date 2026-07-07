package com.example.demo.endpoint.rest.controller.health;

import static com.example.demo.endpoint.rest.controller.health.PingController.OK;

import com.example.demo.PojaGenerated;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@PojaGenerated
@RestController
@AllArgsConstructor
public class HealthEventController {

  @GetMapping(value = "/health/event1")
  public List<String> handleEvent1() {
    return List.of("event-disabled");
  }

  @GetMapping(value = "/health/event/uuids")
  public ResponseEntity<String> checkUuids() {
    return OK;
  }
}
