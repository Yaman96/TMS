package com.ymprog.tms.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ymprog.tms.entities.Task;
import com.ymprog.tms.entities.User;
import com.ymprog.tms.repositories.TaskRepository;

import jakarta.persistence.criteria.Predicate;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> filterTasks(Long id, String title, String description, LocalDateTime creationDateFrom,
            LocalDateTime creationDateTo, LocalDateTime deadlineFrom, LocalDateTime deadlineTo, User assignee,
            User creator, String sortBy) {
        Specification<Task> spec = prepareSearchSpecification(id, title, description, creationDateFrom, creationDateTo,
                deadlineFrom, deadlineTo, assignee, creator);
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
        return taskRepository.findAll(spec, sort);
    }

    private Specification<Task> prepareSearchSpecification(Long id, String title, String description,
            LocalDateTime creationDateFrom, LocalDateTime creationDateTo,
            LocalDateTime deadlineFrom, LocalDateTime deadlineTo, User assignee, User creator) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (id != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), id));
            }
            if (title != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder
                        .like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }
            if (description != null) {
                String[] words = description.split("\\s+");
                Predicate descriptionPredicate = criteriaBuilder.or();
                for (String word : words) {
                    descriptionPredicate = criteriaBuilder.or(descriptionPredicate, criteriaBuilder
                            .like(criteriaBuilder.lower(root.get("description")), "%" + word.toLowerCase() + "%"));
                }
                predicate = criteriaBuilder.and(predicate, descriptionPredicate);
            }
            if (creationDateFrom != null && creationDateTo != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.between(root.get("creationDate"), creationDateFrom, creationDateTo));
            }
            if (deadlineFrom != null && deadlineTo != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.between(root.get("deadline"), deadlineFrom, deadlineTo));
            }
            if (assignee != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("assignee"), assignee));
            }
            if (creator != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("creator"), creator));
            }

            return predicate;
        };
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    

}
