package com.backand.tracker.modules.task.services;

import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.task.dto.res.TaskDto;
import com.backand.tracker.modules.user.User;

import java.util.List;

public interface TaskService {
    List<TaskDto> getAllTaskByProjectId(User user, Long projectId);

    TaskDto getTaskById(User user, Long projectId, Long taskId);

    Task getTaskById(Long id);

    TaskDto createNewTask(User user, String name, String description, Long projectId);

    void deleteTask(User user, Long taskOwnerUserId, Long taskId, Long projectId);

    void addTaskExecutor(User user, Long taskId, Long projectId, Long taskExecutorUserId);

    void deleteTaskExecutor(User user, Long taskId, Long projectId, Long taskExecutorUserId);
}
