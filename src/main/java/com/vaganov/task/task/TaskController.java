package com.vaganov.task.task;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

  private TaskService taskService;


  @Autowired
  public TaskController(TaskService taskService) {
    this.taskService = taskService;

  }
  @PreAuthorize("hasAuthority('user:create')")
  @PostMapping("/newTask")
  public Task newTask (@RequestBody Task newTask){
    return taskService.newTask(newTask);
  }

  @GetMapping("/tasks")
  public Page<Task> getAllTasks(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                @RequestParam(value = "limit", defaultValue = "20") Integer limit){
    return taskService.findTaskPage(offset, limit);
  }

  @GetMapping("/tasks/userAuthor/{id}")
  public  Page<Task> getAllTasksByUserAuthorId(@PathVariable Integer id,
                                               @RequestParam(value = "offset", defaultValue = "0")Integer offset,
                                               @RequestParam(value = "limit", defaultValue = "20")Integer limit){
    return  taskService.findAllTasksByUserAuthorId(offset, limit, id);
  }

  @GetMapping("/tasks/userExecutor/{id}")
  public Page<Task> getAllTasksByUserExecutorId(@PathVariable Integer  id,
                                                @RequestParam(value = "offset", defaultValue = "0")Integer offset,
                                                @RequestParam(value = "limit", defaultValue = "20") Integer limit){
    return  taskService.findAllTasksByUserExecutorId(offset, limit, id);
  }

  @GetMapping("/task/{id}")
  public Task getTaskById(@PathVariable Integer id){
    return taskService.findTaskById(id);
  }


  @PutMapping("/newStatus/{id}")
  public Task updateTaskStatus(@PathVariable Integer id, @RequestBody TaskRequest taskRequest){
    return taskService.updateStatus(taskRequest, id);
  }

  @PreAuthorize("hasAuthority('user:update')")
  @PutMapping("/taskComment/{id}")
  public Task updateTaskComments(@PathVariable Integer id, @RequestBody TaskRequest taskRequest){
    return taskService.updateComments(taskRequest,id);
  }

  @PreAuthorize("hasAuthority('user:update')")
  @PutMapping("/taskToUser/{id}")
  public Task updateTaskExecutor(@RequestBody TaskRequest taskRequest, @PathVariable Integer id){
    return taskService.updateExecutor(taskRequest, id);
  }
  @PreAuthorize("hasAuthority('user:delete')")
  @DeleteMapping("/task/{id}")
  public String deleteTask(@PathVariable Integer id){
    return taskService.delete(id);
  }


}
