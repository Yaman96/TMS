package com.ymprog.tms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ymprog.tms.entities.Task;

public interface TaskRepository extends CrudRepository<Task,Long>{
    List<Task> findByTitleIsContainingAndAssignee_IdOrderByCreationDateAsc(String title, long assigneeId);
    List<Task> findByAssignee_Id(long assignee_id);
}
