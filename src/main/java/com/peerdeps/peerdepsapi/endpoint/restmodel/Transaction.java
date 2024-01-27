package com.peerdeps.peerdepsapi.endpoint.restmodel;

import java.time.Instant;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Transaction {
  private String id;
  private String userId;
  private Double amount;
  private Instant creationDatetime;
  private com.peerdeps.peerdepsapi.model.Transaction.TransactionType type;

}
