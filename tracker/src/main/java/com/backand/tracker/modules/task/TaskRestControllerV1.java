package com.backand.tracker.modules.task;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project.service.ProjectService;
import com.backand.tracker.modules.project_role_permission.ProjectPermission;
import com.backand.tracker.modules.task.dto.req.CreateTaskReqDto;
import com.backand.tracker.modules.task.dto.res.TaskDto;
import com.backand.tracker.modules.task.services.TaskService;
import com.backand.tracker.modules.task_role_permission.TaskPermission;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user.services.UserService;
import com.backand.tracker.utils.UserPermissionsCheck;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/projects/{projectId}/tasks")
public class TaskRestControllerV1 {

    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskRestControllerV1(
            ProjectService projectService,
            TaskService taskService,
            UserService userService,
            TaskMapper taskMapper) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.taskMapper = taskMapper;
        this.userService = userService;
    }

    @Operation(summary = "Возвращает таски по id")
    @GetMapping("/{taskId}")
    ResponseEntity<TaskDto> getById(
            @PathVariable
            Long projectId,
            @PathVariable
            Long taskId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getProjectById(projectId);
        Task task = taskService.getTaskById(taskId);

        TaskDto taskDto = taskMapper.toDto(task);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @Operation(summary = "Возвращает таску по projectId")
    @GetMapping()
    ResponseEntity<Collection<TaskDto>> getAllByProjectId(
            @PathVariable
            Long projectId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getProjectById(projectId);

        List<Task> tasks = taskService.getAllTaskByProjectId(projectId);

        List<TaskDto> taskDtos = tasks.stream().map(taskMapper::toDto).toList();

        return new ResponseEntity<>(taskDtos, HttpStatus.OK);
    }

    @Operation(summary = "Создаёт новую таску")
    @PostMapping()
    ResponseEntity<TaskDto> createNewTask(
            @PathVariable
            Long projectId,
            @RequestBody
            CreateTaskReqDto reqDto,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getProjectById(projectId);

        Task task = taskService
                .createNewTask(
                        user,
                        reqDto.getName(),
                        reqDto.getDescriptions(),
                        project
                );

        TaskDto taskDto = taskMapper.toDto(task);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @Operation(summary = "Удаляет таску")
    @DeleteMapping("/{taskId}")
    ResponseEntity deleteTask(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getProjectById(projectId);
        Task task = taskService.getTaskById(taskId);

        UserPermissionsCheck.inTaskWithException(user, task, TaskPermission.DELETE);

        taskService.deleteTask(taskId);

        return new ResponseEntity("OK", HttpStatus.OK);
    }

//    @Operation(summary = "Добавляет в таску исполнителя")
//    @PostMapping("/{taskId}/executor")
//    ResponseEntity addTaskExecutor(
//            @PathVariable Long projectId,
//            @PathVariable Long taskId,
//            @RequestBody Long taskExecutorId,
//            Principal principal
//    ) {
//        User user = userService.getUserByUsername(principal.getName());
//        Project project = projectService.getById(projectId);
//        Task task = taskService.getTaskById(taskId);
//
//        UserPermissionsCheck.checkUserPermissionInProjectWithException(user, project, ProjectPermissionsEnum.READ);
//        UserPermissionsCheck.checkUserPermissionInTaskWithException(user, task, TaskPermissionsEnum.ADD_USER);
//
//        taskService.addTaskExecutor(user, taskId, projectId, taskExecutorId);
//
//        return new ResponseEntity("OK", HttpStatus.OK);
//    }
//
//    @Operation(summary = "Удаляет исполнителя из таски")
//    @DeleteMapping("/{taskId}/executor")
//    ResponseEntity deleteTaskExecutor(
//            @PathVariable Long projectId,
//            @PathVariable Long taskId,
//            @RequestBody Long taskExecutorId,
//            Principal principal
//    ) {
//        User user = userService.getUserByUsername(principal.getName());
//        Project project = projectService.getById(projectId);
//        Task task = taskService.getTaskById(taskId);
//
//        UserPermissionsCheck.checkUserPermissionInProjectWithException(user, project, ProjectPermissionsEnum.READ);
//        UserPermissionsCheck.checkUserPermissionInTaskWithException(user, task, TaskPermissionsEnum.ADD_USER);
//
//        taskService.deleteTaskExecutor(user, taskId, projectId, taskExecutorId);
//
//        return new ResponseEntity<>("OK", HttpStatus.OK);
//    }

    @Operation(summary = "Изменить таску")
    @PatchMapping("/{taskId}")
    ResponseEntity<TaskDto> updateTask(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @RequestBody Long taskExecutorId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getProjectById(projectId);
        Task task = taskService.getTaskById(taskId);

        UserPermissionsCheck.inTaskWithException(user, task, TaskPermission.UPDATE);

        //TODO

        return new ResponseEntity("OK", HttpStatus.OK);
    }
}
