package com.vaganov.task.task;

import com.vaganov.task.exceptions.TaskNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TackServiceImpl implements TaskService {

  private TaskRepository taskRepository;

  @Autowired
  public TackServiceImpl(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Task newTask (Task newTask){
    return taskRepository.save(newTask);
  }

  public Page<Task> findTaskPage(Integer offset, Integer limit) {

    return taskRepository.findAll(PageRequest.of(offset, limit));
  }


  public Page<Task> findAllTasksByUserAuthorId(Integer offset, Integer limit, Integer id){

    return  taskRepository.findAllByUserAuthorId(id, PageRequest.of(offset, limit));
  }


  public Page<Task> findAllTasksByUserExecutorId(Integer offset, Integer limit, Integer id){

    return  taskRepository.findAllByUserExecutorId(id, PageRequest.of(offset, limit));

  }


  public Task findTaskById(Integer id){
    return taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException(id));
  }


  public Task updateStatus(TaskRequest taskRequest, Integer  id){
    return taskRepository.findById(id).map(task -> {
      task.setStatus(taskRequest.getStatusEnum());
      return  taskRepository.save(task);
    }).orElseThrow(()-> new TaskNotFoundException(id));
  }


  public Task updateComments(TaskRequest taskRequest, Integer  id){
    return taskRepository.findById(id).map(task -> {
      task.getComments().add(taskRequest.getComment());
      return  taskRepository.save(task);
    }).orElseThrow(()-> new TaskNotFoundException(id));
  }

  public Task updateExecutor(TaskRequest taskRequest, Integer id){
    return taskRepository.findById(id).map(task -> {
      task.setUserExecutorId(taskRequest.getUserExecutorId());
      return  taskRepository.save(task);
    }).orElseThrow(()-> new TaskNotFoundException(id));
  }


  public String delete(Integer id){
    if(!taskRepository.existsById(id)){
      throw  new TaskNotFoundException(id);
    }
    taskRepository.deleteById(id);
    return"Id " +id +" deleted successfully!";
  }

}
