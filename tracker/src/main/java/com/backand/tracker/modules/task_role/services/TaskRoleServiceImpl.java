package com.backand.tracker.modules.task_role.services;

import com.backand.tracker.modules.task_role.TaskRole;
import com.backand.tracker.modules.task_role.TaskRoleRepository;
import com.backand.tracker.modules.task.services.TaskService;
import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TaskRoleServiceImpl implements TaskRoleService {

    private final TaskRoleRepository taskRoleRepository;
    private final TaskService taskService;

    @Autowired
    public TaskRoleServiceImpl(
            TaskRoleRepository taskRoleRepository,
            TaskService taskService
    ) {
        this.taskRoleRepository = taskRoleRepository;
        this.taskService = taskService;
    }

    @Override
    public TaskRole createNew(
            String name,
            User creator,
            Long id,
            Long projectId,
            Long taskId
    ) {
        Task task = taskService.getById(taskId);

        TaskRole taskRole = new TaskRole(name, creator, task);

        return taskRoleRepository.save(taskRole);
    }

    @Override
    public void delete(
            User user,
            Long id,
            Long taskId,
            Long projectId,
            Long taskRoleId
    ) {
        Task task = taskService.getById(taskId);

        taskRoleRepository.deleteById(taskRoleId);
    }

    @Override
    public Collection<TaskRole> getAllByTask(User user, Long taskId) {
        return null;
    }

    @Override
    public TaskRole getById(User user, Long id, Long taskId) {
        return null;
    }
}
