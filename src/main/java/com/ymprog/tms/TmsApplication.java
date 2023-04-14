package com.ymprog.tms;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ymprog.tms.entities.Task;
import com.ymprog.tms.entities.User;
import com.ymprog.tms.repositories.UserRepository;
import com.ymprog.tms.services.TaskService;
import com.ymprog.tms.services.TaskServiceImpl;

@SpringBootApplication
public class TmsApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TmsApplication.class, args);

		TaskService taskService = context.getBean(TaskServiceImpl.class);

		UserRepository userRepository = context.getBean(UserRepository.class);

		User assignee = new User("test_username1", "adm123", "email1", "test_fn1", "test_ln1", LocalDateTime.now(), false);
		User creator = new User("test_username2", "adm123", "email2", "test_fn2", "test_ln2", LocalDateTime.now(), false);
		userRepository.save(assignee);
		userRepository.save(creator);
		Task task1 = new Task("title1", "descr1", LocalDateTime.now(), LocalDateTime.of(2023, 6, 14, 0, 0, 0), assignee, creator);

		taskService.save(task1);

		
	
	}

}
