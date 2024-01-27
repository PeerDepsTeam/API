package com.peerdeps.peerdepsapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Course implements Serializable {
  @Id
  private String id;
  private String module;
  private String content;
  private String requirements;
  private String banner;
  private String description;
}
