package com.vaganov.task.exceptions;

public class TaskNotFoundException extends RuntimeException{
  public TaskNotFoundException(Integer id){
    super("Could not the task with id: " +id);
  }
}
