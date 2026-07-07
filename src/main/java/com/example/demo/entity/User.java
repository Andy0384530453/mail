package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String username;

  private String firstName;

  private String lastName;

  @Column(unique = true)
  private String email;
}
