package com.vaganov.task.task;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@RequestMapping("/api/v1/tasks")
@EntityListeners(AuditingEntityListener.class)
public class Task {

  @Id
  @GeneratedValue
  private Integer id;
  private String name;
  private String description;
  @Enumerated(EnumType.ORDINAL)
  private Status status;
  @Enumerated(EnumType.STRING)
  private Priority priority;
  @CreatedBy
  @Column(
      nullable = false,
      updatable = false
  )
  private Integer userAuthorId;
  @LastModifiedBy
  @Column(insertable = false)
  private Integer userExecutorId;

  @OneToMany(cascade= CascadeType.ALL)
  private List<Comment> comments;


}
