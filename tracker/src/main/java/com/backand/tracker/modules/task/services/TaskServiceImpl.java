package com.backand.tracker.modules.task.services;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project_role_permission.ProjectPermissionsEnum;
import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.task.TaskMapper;
import com.backand.tracker.modules.task.dto.res.TaskDto;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.task.TaskRepository;
import com.backand.tracker.modules.project.service.ProjectService;
import com.backand.tracker.modules.user_project.services.UserProjectService;
import com.backand.tracker.utils.UserPermissionsCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectService projectService;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskServiceImpl(
            TaskRepository taskRepository,
            ProjectService projectService,
            TaskMapper taskMapper
    ) {
        this.taskRepository = taskRepository;
        this.projectService = projectService;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskDto> getAllTaskByProjectId(
            User user,
            Long projectId
    ) {
        Project project = projectService.getById(
                projectId
        );

        UserPermissionsCheck
                .checkUserPermissionInProjectWithException(
                        user,
                        project,
                        ProjectPermissionsEnum.READ
                );

        List<Task> tasks = (List<Task>) taskRepository.getTaskByProjectId(projectId);

        List<TaskDto> taskDtos = tasks
                .stream()
                .map(taskMapper::toDto).toList();

        return taskDtos;
    }

    @Override
    public TaskDto getTaskById(
            User user,
            Long projectId,
            Long taskId
    ) {
        Project project = projectService.getById(projectId);

        UserPermissionsCheck
                .checkUserPermissionInProjectWithException(
                        user,
                        project,
                        ProjectPermissionsEnum.READ
                );

        Task task = taskRepository
                .getTaskByIdAndProjectId(taskId, projectId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found!"));
        return taskMapper.toDto(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found!"));
    }

    @Override
    public TaskDto createNewTask(
            User user,
            String name,
            String description,
            Long projectId
    ) {
        Project project = projectService.getById(projectId);

        UserPermissionsCheck
                .checkUserPermissionInProjectWithException(
                        user,
                        project,
                        ProjectPermissionsEnum.CREATE_TASK
                );

        Task task = new Task(name,
                description,
                project,
                user
        );

        Task saveTask = taskRepository.save(task);
        return taskMapper.toDto(saveTask);
    }

    @Override
    public void deleteTask(
            User user,
            Long taskOwnerUserId,
            Long taskId,
            Long projectId
    ) {
        Project project = projectService.getById(projectId);

        UserPermissionsCheck
                .checkUserPermissionInProjectWithException(
                        user,
                        project,
                        ProjectPermissionsEnum.DELETE_TASK
                );

        taskRepository.deleteById(taskId);
    }

    @Override
    public void addTaskExecutor(User user, Long taskId, Long projectId, Long taskExecutorUserId) {

    }

    @Override
    public void deleteTaskExecutor(User user, Long taskId, Long projectId, Long taskExecutorUserId) {

    }

//    @Override
//    public void addTaskExecutor(
//            User user,
//            Long taskId,
//            Long projectId,
//            Long taskExecutorUserId
//    ) {
//        Project project = projectService.getById(
//                user,
//                projectId
//        );
//        Task task = taskService.getTaskById(user,
//                taskId,
//                projectId
//        );
//
//        UserPermissionsCheck
//                .checkUserPermissionInTaskWithException(
//                        user,
//                        task,
//                        TaskPermissionsEnum.ADD_USER
//                );
//
//        //UserProject userProject = new UserProject(user, project, baseUserProjectRole);
//    }
//
//    @Override
//    public void deleteTaskExecutor(
//            User user,
//            Long taskId,
//            Long projectId,
//            Long taskExecutorUserId
//    ) {
//        Project project = projectService.getById(
//                user,
//                projectId
//        );
//        Task task = taskService.getTaskById(user,
//                taskId,
//                projectId
//        );
//
//        UserPermissionsCheck
//                .checkUserPermissionInTaskWithException(
//                        user,
//                        task,
//                        TaskPermissionsEnum.DELETE_USER
//                );
//
//        // UserProject userProject = userProjectService
//    }

}
