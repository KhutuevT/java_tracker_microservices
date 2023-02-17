package com.backand.tracker.modules.task.services;

import com.backand.tracker.annotations.CheckProjectPermissions;
import com.backand.tracker.annotations.CheckTaskPermissions;
import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project.service.ProjectService;
import com.backand.tracker.modules.project_role_permission.ProjectPermission;
import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.task.TaskMapper;
import com.backand.tracker.modules.task.dto.res.TaskDto;
import com.backand.tracker.modules.task_role_permission.TaskPermission;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectService projectService;

    @Autowired
    public TaskServiceImpl(
            TaskRepository taskRepository,
            TaskMapper taskMapper,
            ProjectService projectService) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectService = projectService;
    }

    @CheckProjectPermissions(permission = ProjectPermission.READ)
    @Override
    public List<TaskDto> getAllDtoByProjectId(Long projectId) {
        List<Task> tasks = (List<Task>) taskRepository.getTaskByProjectId(projectId);
        List<TaskDto> taskDtos =  tasks.stream().map(taskMapper::toDto).toList();
        return taskDtos;
    }


    @CheckProjectPermissions(permission = ProjectPermission.READ)
    @Override
    public TaskDto getDtoById(Long taskId) {
        Task task = getById(taskId);
        return taskMapper.toDto(task);
    }

    @CheckProjectPermissions(permission = ProjectPermission.READ)
    @Override
    public Task getById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found!"));
    }


    @CheckProjectPermissions(permission = ProjectPermission.CREATE_TASK)
    @Override
    public TaskDto createNew(
            Long projectId,
            User user,
            String name,
            String description
    ) {
        Project project = projectService.getById(projectId);
        Task task = new Task(name,
                description,
                project,
                user
        );

        Task saveTask = taskRepository.save(task);
        TaskDto taskDto = taskMapper.toDto(saveTask);
        return taskDto;
    }

    @CheckTaskPermissions(permission = TaskPermission.DELETE)
    @Override
    public void delete(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
