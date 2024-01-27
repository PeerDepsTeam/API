package com.peerdeps.peerdepsapi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Transaction implements Serializable {
  @Id
  private String id;
  @ManyToOne(cascade = CascadeType.ALL)
  private User user;
  private Double amount;
  @Timestamp
  private Instant creationDatetime;

  @Enumerated(EnumType.STRING)
  private TransactionType type;

  public enum TransactionType {
    INCOME, OUTCOME
  }

}
