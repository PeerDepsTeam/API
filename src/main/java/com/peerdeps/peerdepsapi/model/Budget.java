package com.peerdeps.peerdepsapi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import java.io.Serializable;
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
public class Budget implements Serializable {
  @Id
  private String id;
  @JoinColumn(referencedColumnName = "id")
  private String userId;
  private Double initialCapital;
  @Transient
  private Double currentCapital;
  @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
  private Savings savings;

  @PrePersist
  public void initialCapital(){
    initialCapital = 10000.0;
  }
}
