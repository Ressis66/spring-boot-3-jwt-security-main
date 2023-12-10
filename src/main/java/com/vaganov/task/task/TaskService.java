package com.vaganov.task.task;

import org.springframework.data.domain.Page;

public interface TaskService {
  Task newTask ( Task newTask);
  Page<Task> findTaskPage(Integer offset, Integer limit);
  Page<Task> findAllTasksByUserAuthorId(Integer offset, Integer limit, Integer id);
  Page<Task> findAllTasksByUserExecutorId(Integer offset, Integer limit,Integer id);
  Task findTaskById(Integer  id);
  Task updateStatus(TaskRequest taskRequest, Integer  id);
  Task updateComments(TaskRequest taskRequest, Integer  id);
  Task updateExecutor(TaskRequest taskRequest, Integer id);
  String delete(Integer  id);
}
