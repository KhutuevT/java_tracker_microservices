package com.backand.tracker.modules.task.services;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.task.dto.res.TaskDto;
import com.backand.tracker.modules.user.User;

import java.util.List;

public interface TaskService {
    List<Task> getAllTaskByProjectId(Long projectId);

    Task getTaskById(Long id);

    Task createNewTask(User user, String name, String description, Project project);

    void deleteTask(Long taskId);
}
