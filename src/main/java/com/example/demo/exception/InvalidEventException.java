package com.example.demo.exception;

public class InvalidEventException extends RuntimeException {
  public InvalidEventException(String message) {
    super(message);
  }
}
