package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.model.Status;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // ИСПРАВЛЕННЫЙ Метод для создания новой задачи
    public Task createTask(Task task) {
        // Проверяем: если у пришедшей задачи статус равен null
        // (то есть в Swagger или фронтенде его не заполнили)
        if (task.getStatus() == null) {
            task.setStatus(Status.TODO); // Ставим по умолчанию TODO
        }

        // Если статус был передан (например, IN_PROGRESS или DONE),
        // условие выше пропустится, и задача сохранится с твоим статусом!
        return taskRepository.save(task);
    }

    // Метод для получения задач по статусу
    public List<Task> getTasksByStatus(Status status) {
        if (status != null) {
            return taskRepository.findByStatus(status);
        }
        return taskRepository.findAll();
    }
}