package com.backand.tracker.modules.user_task.services;

import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user_task.UserTask;
import com.backand.tracker.modules.user_task.UserTaskRepository;
import com.backand.tracker.modules.user_task.services.UserTaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTaskServiceImpl implements UserTaskService {

    public static UserTaskRepository userTaskRepository;

    @Autowired
    public UserTaskServiceImpl(UserTaskRepository userTaskRepository){
        this.userTaskRepository = userTaskRepository;
    }

    @Override
    public List<UserTask> getAllUserTaskByTaskId(Long taskId) {
        return null;
    }

    @Override
    public UserTask getUserTaskById(Long id) {
        return null;
    }

    @Override
    public UserTask getByTaskIdAndUserId(Long taskId, Long userId) {
        return userTaskRepository.findByTaskIdAndUserId(taskId, userId).orElseThrow(() -> new EntityNotFoundException("User task not found!"));
    }

    @Override
    public void addTaskExecutor(User user, Long taskId, Long projectId, Long taskExecutorUserId) {

    }

    @Override
    public void deleteTaskExecutor(User user, Long taskId, Long projectId, Long taskExecutorUserId) {

    }
}
