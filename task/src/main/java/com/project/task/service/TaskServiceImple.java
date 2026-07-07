package com.project.task.service;

import com.project.task.dto.TaskRequest;
import com.project.task.dto.TaskResponse;
import com.project.task.exception.TaskNotExistException;
import com.project.task.model.Status;
import com.project.task.model.Task;
import com.project.task.repository.TaskRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImple implements TaskService{
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TaskRepo taskRepo;

    @Override
    public List<TaskResponse> getAll() {
        List<Task> list = taskRepo.findAll();
        if(list.isEmpty())
            throw new TaskNotExistException("There is no task uploaded");

        List<TaskResponse> res = list.stream()
                .map(task->
                {
                    TaskResponse r = modelMapper.map(task,TaskResponse.class);
                    r.setStatus(task.getStatus().getEnumToString());
                    return r;
                })
                .toList();
        return res;
    }

    @Override
    public TaskResponse getById(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(()->new TaskNotExistException("Task does not exist by id "+id));
        TaskResponse res = modelMapper.map(task,TaskResponse.class);
        res.setStatus(task.getStatus().getEnumToString());
        return res;
    }

    @Override
    public TaskResponse add(TaskRequest request) {
        Task task = modelMapper.map(request,Task.class);
        task.setCreated_at(LocalDateTime.now());
        task.setUpdated_at(LocalDateTime.now());
        task.setStatus(Status.getStringToEnum(request.getStatus()));

        Task st = taskRepo.save(task);

        TaskResponse t = modelMapper.map(st,TaskResponse.class);
        t.setStatus(st.getStatus().getEnumToString());
        return  t;

    }

    @Override
    public TaskResponse updateById(Long id,TaskRequest request) {
        Task task = taskRepo.findById(id).orElseThrow(()->new TaskNotExistException("Task does not exist by id "+id));
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDue_date(request.getDue_date());

        task.setStatus(Status.getStringToEnum(request.getStatus()));

        task.setUpdated_at(LocalDateTime.now());
        Task st = taskRepo.save(task);

        TaskResponse t = modelMapper.map(st,TaskResponse.class);
        t.setStatus(st.getStatus().getEnumToString());
        return  t;

    }

    @Override
    public void deleteById(Long id) {

        Task task = taskRepo.findById(id).orElseThrow(()->new TaskNotExistException("Task does not exist by id "+id));
        taskRepo.deleteById(id);



    }

    @Override
    public TaskResponse PatchById(Long id,String status) {
        Task task = taskRepo.findById(id).orElseThrow(()->new TaskNotExistException("Task does not exist by id "+id));

        task.setStatus(Status.getStringToEnum(status));
        Task result  =  taskRepo.save(task);

        TaskResponse t = modelMapper.map(result,TaskResponse.class);
        t.setStatus(result.getStatus().getEnumToString());
        t.setCreated_at(LocalDateTime.now());
        return  t;
    }
}
