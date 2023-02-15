package com.backand.tracker.modules.time_slice;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project.service.ProjectService;
import com.backand.tracker.modules.project_role_permission.ProjectPermission;
import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.task.services.TaskService;
import com.backand.tracker.modules.task_role_permission.TaskPermission;
import com.backand.tracker.modules.time_slice.services.TimeSliceService;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.time_slice.dto.req.TimeSliceStartReqDto;
import com.backand.tracker.modules.user.services.UserService;
import com.backand.tracker.utils.UserPermissionsCheck;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/projects/{projectId}/tasks/{taskId}/time-slice")
public class TimeSliceRestControllerV1 {

    private TimeSliceService timeSliceService;
    private UserService userService;
    private TaskService taskService;
    private ProjectService projectService;
    @Autowired
    public TimeSliceRestControllerV1(
            TimeSliceService timeSliceService,
            ProjectService projectService,
            TaskService taskService,
            UserService userService) {
        this.timeSliceService = timeSliceService;
        this.taskService = taskService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @Operation(summary = "Возвращает все time-slice таски")
    @GetMapping
    public ResponseEntity getAll(
            @PathVariable
            Long projectId,
            @PathVariable
            Long taskId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getProjectById(projectId);

        List<TimeSlice> timeSliceList = timeSliceService.getTaskTimeSlices(taskId);
        return new ResponseEntity(timeSliceList, HttpStatus.OK);
    }

    @Operation(summary = "Возвращает time-slice по id")
    @GetMapping("/{timeSliceId}")
    public ResponseEntity getById(
            @PathVariable
            Long projectId,
            @PathVariable
            Long taskId,
            @PathVariable
            Long timeSliceId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getProjectById(projectId);

        TimeSlice timeSlice = timeSliceService.getById(timeSliceId);

        return new ResponseEntity(timeSlice, HttpStatus.OK);
    }

    @Operation(summary = "Начинает трекать время")
    @PostMapping("/start")
    ResponseEntity start(
            @PathVariable
            Long projectId,
            @PathVariable
            Long taskId,
            @RequestBody
            TimeSliceStartReqDto reqDto,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getProjectById(projectId);
        Task task = taskService.getTaskById(taskId);

        TimeSlice timeSlice = timeSliceService
                .start(user, projectId, taskId, reqDto.getName());

        return new ResponseEntity(timeSlice, HttpStatus.OK);
    }

    @Operation(summary = "Останавливает трекание времени")
    @PostMapping("/stop")
    ResponseEntity stop(
            @PathVariable
            Long projectId,
            @PathVariable
            Long taskId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getProjectById(projectId);
        Task task = taskService.getTaskById(taskId);

        TimeSlice timeSlice = timeSliceService
                .stop(user, projectId, taskId);

        return new ResponseEntity(timeSlice, HttpStatus.OK);
    }
}
