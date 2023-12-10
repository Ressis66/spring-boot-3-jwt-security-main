package com.vaganov.task.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;


public enum Priority implements Serializable {
  HIGH,
  MIDDLE,
  LOW;

  public String getStatus() {
    return this.name();
  }
}
