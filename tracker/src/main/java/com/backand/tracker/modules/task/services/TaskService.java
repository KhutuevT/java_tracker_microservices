package com.backand.tracker.modules.task.services;

import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.task.dto.req.CreateTaskReqDto;
import com.backand.tracker.modules.task.dto.res.TaskDto;
import com.backand.tracker.modules.user.User;

import java.util.List;

public interface TaskService {
    String test();
    List<TaskDto> getAllDtoByProjectId(Long projectId);
    TaskDto getDtoById(Long taskId);
    Task getById(Long taskId);
    TaskDto createNew(Long projectId, User user, CreateTaskReqDto taskReqDto);
    void delete(Long taskId);
}
