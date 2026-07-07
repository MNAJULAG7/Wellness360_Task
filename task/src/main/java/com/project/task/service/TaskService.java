package com.project.task.service;

import com.project.task.dto.TaskRequest;
import com.project.task.dto.TaskResponse;

import java.util.List;

public interface TaskService {
    List<TaskResponse> getAll();

    TaskResponse getById(Long id);

    TaskResponse add(TaskRequest request);

    TaskResponse updateById(Long id,TaskRequest request);

    void deleteById(Long id);

    TaskResponse PatchById(Long id,String status);
}
