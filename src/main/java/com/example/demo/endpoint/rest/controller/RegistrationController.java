package com.example.demo.endpoint.rest.controller;

import com.example.demo.endpoint.rest.RegistrationRequest;
import com.example.demo.exception.InvalidEventException;
import com.example.demo.service.RegistreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class RegistrationController {

  private final RegistreService registreService;

  @PostMapping("/registrations")
  public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
    try {
      registreService.register(request);
      return ResponseEntity.status(HttpStatus.CREATED)
          .body("Registration successful, check your email!");
    } catch (InvalidEventException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}
