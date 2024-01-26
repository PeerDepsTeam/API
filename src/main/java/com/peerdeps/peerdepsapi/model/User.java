package com.peerdeps.peerdepsapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.Instant;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class User implements Serializable {
  @Id
  private String id;
  private String firstName;
  private String lastName;
  private String sex;
  private String username;
  @Timestamp
  private Instant birthdate;
}
