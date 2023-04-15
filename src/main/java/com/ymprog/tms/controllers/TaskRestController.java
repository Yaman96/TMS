package com.ymprog.tms.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymprog.tms.entities.User;
import com.ymprog.tms.security.TokenProvider;
import com.ymprog.tms.services.TaskListJsonResult;
import com.ymprog.tms.services.TaskService;

@RestController
@RequestMapping("/users/tasks")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TokenProvider tokenProvider;

    @GetMapping("/search")
public ResponseEntity<TaskListJsonResult> searchTasks(
        @RequestHeader("Authorization") String authHeader,
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
    String token = authHeader.substring(7); // Remove "Bearer " prefix
    Long id = tokenProvider.getUserIdFromToken(token);
    TaskListJsonResult result = new TaskListJsonResult(taskService.filterTasks(id, title, description, creationDateFrom, creationDateTo, deadlineFrom, deadlineTo, assignee, creator, sortBy));
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
}
}
