package com.backand.tracker.modules.task_role.services;

import com.backand.tracker.modules.task_role.TaskRole;
import com.backand.tracker.modules.task_role.TaskRoleRepository;
import com.backand.tracker.modules.task.services.TaskService;
import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.task_role_permission.TaskPermissionsEnum;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.project.service.ProjectService;
import com.backand.tracker.modules.task_role.services.TaskRoleService;
import com.backand.tracker.utils.UserPermissionsCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TaskRoleServiceImpl implements TaskRoleService {

    private final ProjectService projectService;
    private final TaskRoleRepository taskRoleRepository;
    private final TaskService taskService;

    @Autowired
    public TaskRoleServiceImpl(
            ProjectService projectService,
            TaskRoleRepository taskRoleRepository,
            TaskService taskService
    ) {
        this.projectService = projectService;
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
        Task task = taskService.getTaskById(taskId);

        UserPermissionsCheck
                .checkUserPermissionInTaskWithException(
                        creator, task, TaskPermissionsEnum.CREATE_ROLE
                );
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
        Task task = taskService.getTaskById(taskId);

        UserPermissionsCheck
                .checkUserPermissionInTaskWithException(
                        user, task, TaskPermissionsEnum.DELETE_ROLE
                );
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
