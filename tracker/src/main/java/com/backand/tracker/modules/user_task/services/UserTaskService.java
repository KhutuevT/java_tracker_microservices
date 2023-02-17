package com.backand.tracker.modules.user_task.services;

import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user_task.UserTask;

import java.util.Collection;
import java.util.List;

public interface UserTaskService {
    List<UserTask> getAllUserTaskByTaskId(Long taskId);

    UserTask getUserTaskById(Long id);

    UserTask getByTaskIdAndUserId(Long taskId, Long userId);

    void addTaskExecutor(User user, Long taskId, Long projectId, Long taskExecutorUserId);

    void deleteTaskExecutor(User user, Long taskId, Long projectId, Long taskExecutorUserId);
}
