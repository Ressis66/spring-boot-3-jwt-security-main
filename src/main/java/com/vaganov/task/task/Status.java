package com.vaganov.task.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// custom serializer
public enum Status {

  WAITING("waiting"),

  IN_PROGRESS("in_progress"),

  COMPLETED("completed");

  private String value;

  Status(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Status forValue(String value) {
    return Stream.of(Status.values())
        .filter(enumValue -> enumValue.name().equals(value.toUpperCase()))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
