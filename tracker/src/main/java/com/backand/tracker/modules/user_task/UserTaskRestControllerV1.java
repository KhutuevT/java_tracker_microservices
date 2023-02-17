package com.backand.tracker.modules.user_task;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project.service.ProjectService;
import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.task.services.TaskService;
import com.backand.tracker.modules.user.services.UserService;
import com.backand.tracker.modules.user_task.dto.res.UserTaskDto;
import com.backand.tracker.modules.user_task.services.UserTaskService;
import com.backand.tracker.modules.user.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/projects/{projectId}/tasks/{taskId}/user-tasks")
public class UserTaskRestControllerV1 {

    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;
    private final UserTaskMapper userTaskMapper;
    private UserTaskService userTaskService;

    @Autowired
    public UserTaskRestControllerV1(
            UserTaskService userTaskService,
            ProjectService projectService,
            TaskService taskService,
            UserService userService,
            UserTaskMapper userTaskMapper
    ) {
        this.userTaskService = userTaskService;
        this.taskService = taskService;
        this.projectService = projectService;
        this.userTaskMapper = userTaskMapper;
        this.userService = userService;
    }

    @Operation(summary = "Возвращает исполнителей по taskId")
    @GetMapping()
    public ResponseEntity get(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getById(projectId);
        Task task = taskService.getById(taskId);

        List<UserTask> userTaskList = userTaskService.getAllUserTaskByTaskId(taskId);
        List<UserTaskDto> userTaskDtos = userTaskList.stream().map(userTaskMapper::toDto).toList();

        return new ResponseEntity(userTaskDtos, HttpStatus.OK);
    }

    @Operation(summary = "Возвращает исполнителя по по userTaskId")
    @GetMapping("/{userTaskId}")
    public ResponseEntity getById(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @PathVariable Long userTaskId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getById(projectId);
        Task task = taskService.getById(taskId);

        UserTask userTask = userTaskService.getUserTaskById(userTaskId);
        UserTaskDto userTaskDto = userTaskMapper.toDto(userTask);

        return new ResponseEntity(userTaskDto, HttpStatus.OK);
    }

    @Operation(summary = "Добавляет в таску исполнителя")
    @PostMapping()
    ResponseEntity addTaskExecutor(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @RequestBody Long taskExecutorId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getById(projectId);
        Task task = taskService.getById(taskId);

        userTaskService.addTaskExecutor(user, taskId, projectId, taskExecutorId);

        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @Operation(summary = "Удаляет исполнителя из таски")
    @DeleteMapping()
    ResponseEntity deleteTaskExecutor(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @RequestBody Long taskExecutorId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getById(projectId);
        Task task = taskService.getById(taskId);

        userTaskService.deleteTaskExecutor(user, taskId, projectId, taskExecutorId);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
