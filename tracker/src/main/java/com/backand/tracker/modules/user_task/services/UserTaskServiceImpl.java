package com.backand.tracker.modules.user_task.services;

import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user_task.UserTask;
import com.backand.tracker.modules.user_task.services.UserTaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTaskServiceImpl implements UserTaskService {
    @Override
    public List<UserTask> getAllUserTaskByTaskId(Long taskId) {
        return null;
    }

    @Override
    public UserTask getUserTaskById(Long id) {
        return null;
    }

    @Override
    public void addTaskExecutor(User user, Long taskId, Long projectId, Long taskExecutorUserId) {

    }

    @Override
    public void deleteTaskExecutor(User user, Long taskId, Long projectId, Long taskExecutorUserId) {

    }
}
