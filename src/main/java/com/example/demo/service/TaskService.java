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

    // Ідеальній і чистий метод для створення нової завдання
    public Task createTask(Task task) {
        // Контролер або модель вже гарантовано поставили статус TODO, якщо його не було.
        // Сервіс просто бере готовий об'єкт і передає його в репозиторій для збереження.
        return taskRepository.save(task);
    }

    // Метод для отримання завдань за статусом
    public List<Task> getTasksByStatus(Status status) {
        if (status != null) {
            return taskRepository.findByStatus(status);
        }
        return taskRepository.findAll(); // Якщо статус не вказано — повернути всі завдання
    }
}