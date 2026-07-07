package com.example.demo.endpoint.rest;

import java.util.UUID;

public record RegistrationRequest(UUID userId, UUID courseId, String eventName) {}
