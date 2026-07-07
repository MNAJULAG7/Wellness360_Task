package com.project.task.dto;

import com.project.task.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDate due_date;
    private String status;
    private LocalDateTime created_at;
    private LocalDateTime  updated_at;
}
