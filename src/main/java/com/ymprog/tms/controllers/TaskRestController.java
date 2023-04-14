package com.ymprog.tms.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymprog.tms.entities.Task;
import com.ymprog.tms.entities.User;
import com.ymprog.tms.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/search")
    public List<Task> searchTasks(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime creationDateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime creationDateTo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deadlineFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deadlineTo,
            @RequestParam(required = false) User assignee,
            @RequestParam(required = false) User creator,
            @RequestParam(required = false, defaultValue = "deadline") String sortBy
    ) {
        return taskService.filterTasks(id, title, description, creationDateFrom, creationDateTo, deadlineFrom, deadlineTo, assignee, creator, sortBy);
    }
}
