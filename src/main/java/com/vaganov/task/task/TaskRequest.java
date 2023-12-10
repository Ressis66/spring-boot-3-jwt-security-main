package com.vaganov.task.task;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class TaskRequest {
  @NotNull
  @NotBlank
  private String name;
  @NotNull
  @NotBlank
  private String description;
  @Enumerated(EnumType.STRING)
  private Status statusEnum;
  @Enumerated(EnumType.STRING)
  private Priority priority;
  private Integer userAuthorId;
  private Integer userExecutorId;
  private Comment comment;
}
