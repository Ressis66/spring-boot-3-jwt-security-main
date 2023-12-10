package com.vaganov.task.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment implements Serializable {

  @Id
  @GeneratedValue
  private Integer id;
  private String description;
  @CreatedBy
  @Column(
      nullable = false,
      updatable = false
  )
  private Integer userAuthorId;

}
