package com.example.demo.dto;

import com.example.demo.model.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;

public class TaskCreateRequest {

    @NotBlank(message = "Назва завдання не може бути порожньою")
    private String title;

    private String description;
    private String subject;

    @NotNull(message = "Дата дедлайну є обов'язковою")
    @FutureOrPresent(message = "Дата дедлайну не може бути в минулому") // Тест-кейс №2!
    private LocalDateTime dueDate;

    @NotNull(message = "Пріоритет є обов'язковим")
    private Priority priority;

    // Геттеры и сеттеры
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
}