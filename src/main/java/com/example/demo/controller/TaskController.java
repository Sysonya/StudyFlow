package com.example.demo.controller;

import com.example.demo.dto.TaskCreateRequest;
import com.example.demo.model.Task;
import com.example.demo.model.Status;
import com.example.demo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    // Внедрение сервиса через конструктор (Constructor Injection)
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Ендпоінт 1: Створення нового завдання (POST /api/v1/tasks)
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskCreateRequest request) {
        // Перекладываем данные из DTO в модель базы данных
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setSubject(request.getSubject());
        task.setDueDate(request.getDueDate());
        task.setPriority(request.getPriority());

        Task savedTask = taskService.createTask(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED); // Возвращает 201 Created
    }

    // Ендпоінт 2: Отримання списку завдань (GET /api/v1/tasks?status=TODO)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getTasks(@RequestParam(required = false) Status status) {
        List<Task> tasks = taskService.getTasksByStatus(status);

        // Формируем обертку JSON в точном соответствии с контрактом из README
        Map<String, Object> response = new HashMap<>();
        response.put("tasks", tasks);
        response.put("totalCount", tasks.size());

        return ResponseEntity.ok(response); // Возвращает 200 OK с телом
    }
}