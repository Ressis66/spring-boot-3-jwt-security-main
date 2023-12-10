package com.vaganov.task.task;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Integer >, JpaRepository<Task,Integer > {

  Page<Task> findAllByUserExecutorId(Integer  id, Pageable pageable);
  Page<Task> findAllByUserAuthorId(Integer  id, Pageable pageable);

}
