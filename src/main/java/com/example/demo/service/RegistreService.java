package com.example.demo.service;

import com.example.demo.endpoint.rest.RegistrationRequest;
import com.example.demo.entity.Course;
import com.example.demo.entity.Registration;
import com.example.demo.entity.User;
import com.example.demo.exception.InvalidEventException;
import com.example.demo.mail.Email;
import com.example.demo.mail.Mailer;
import com.example.demo.repository.CourseRepo;
import com.example.demo.repository.RegistreRepo;
import com.example.demo.repository.UserRepo;
import jakarta.mail.internet.InternetAddress;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistreService {

  private final UserRepo userRepo;
  private final CourseRepo courseRepo;
  private final RegistreRepo registreRepo;
  private final Mailer mailer;

  @SneakyThrows
  public Registration register(RegistrationRequest request) {
    if (!"REGISTRATION".equals(request.eventName())) {
      throw new InvalidEventException("Invalid event: " + request.eventName());
    }

    User user =
        userRepo
            .findById(request.userId())
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + request.userId()));
    Course course =
        courseRepo
            .findById(request.courseId())
            .orElseThrow(
                () -> new IllegalArgumentException("Course not found: " + request.courseId()));

    Registration registration =
        Registration.builder().user(user).course(course).confirmed(false).build();
    Registration saved = registreRepo.save(registration);

    var recipient = new InternetAddress(user.getEmail());
    var email =
        new Email(
            recipient,
            List.of(),
            List.of(),
            "Registration confirmation",
            "You have been registered to: " + course.getTitle(),
            List.of());
    mailer.accept(email);

    return saved;
  }
}
