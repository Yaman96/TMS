package com.ymprog.tms.services;

import java.time.LocalDateTime;
import java.util.List;

import com.ymprog.tms.entities.Task;
import com.ymprog.tms.entities.User;

public interface TaskService {
    List<Task> filterTasks(Long id, String title, String description, LocalDateTime creationDateFrom, LocalDateTime creationDateTo,
    LocalDateTime deadlineFrom, LocalDateTime deadlineTo, User assignee, User creator, String sortBy);
    void save(Task task);
}
