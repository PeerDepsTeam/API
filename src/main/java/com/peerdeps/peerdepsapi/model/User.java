package com.peerdeps.peerdepsapi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "\"user\"")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
public class User implements Serializable {
  @Id
  private String id;
  private String firstName;
  private String lastName;
  private String email;
  @Enumerated(EnumType.STRING)
  private Sex sex;
  private String username;
  @Timestamp
  private Instant birthdate;
  @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
  private Budget budget;

  public enum Sex {
    M, F
  }
}
