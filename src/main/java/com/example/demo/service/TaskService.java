package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.model.Status;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // Это и есть Constructor Injection — Spring сам передаст сюда нужный репозиторий
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Метод для создания новой задачи
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Метод для получения задач по статусу
    public List<Task> getTasksByStatus(Status status) {
        if (status != null) {
            return taskRepository.findByStatus(status);
        }
        return taskRepository.findAll(); // Если статус не указан — вернуть все задачи
    }
}