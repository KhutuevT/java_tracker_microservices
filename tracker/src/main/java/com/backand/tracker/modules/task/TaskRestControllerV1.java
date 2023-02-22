package com.backand.tracker.modules.task;

import com.backand.tracker.annotations.CurrentUser;
import com.backand.tracker.modules.task.dto.req.CreateTaskReqDto;
import com.backand.tracker.modules.task.dto.res.TaskDto;
import com.backand.tracker.modules.task.services.TaskService;
import com.backand.tracker.modules.user.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/projects/{projectId}/tasks")
public class TaskRestControllerV1 {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskRestControllerV1(
            TaskService taskService,
            TaskMapper taskMapper
    ) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping("/test")
    ResponseEntity<?> test(){
        //String test = taskService.test();
        //System.out.println(test);
        TaskDto taskDto = taskService.getDtoById(1L);
        System.out.println(taskDto);
        return new ResponseEntity<>("qwe", HttpStatus.OK);
    }

    @Operation(summary = "Возвращает таски по id")
    @GetMapping("/{taskId}")
    ResponseEntity<TaskDto> getById(
            @PathVariable
            Long projectId,
            @PathVariable
            Long taskId
    ) {
        System.out.println("Возвращает таски по id");
        TaskDto taskDto = taskService.getDtoById(taskId);

        System.out.println(taskDto);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @Operation(summary = "Возвращает таску по projectId")
    @GetMapping()
    ResponseEntity<List<TaskDto>> getAllByProjectId(
            @PathVariable
            Long projectId
    ) {
        List<TaskDto> taskDtos = taskService.getAllDtoByProjectId(projectId);

        return new ResponseEntity(taskDtos, HttpStatus.OK);
    }

    @Operation(summary = "Создаёт новую таску")
    @PostMapping()
    ResponseEntity<TaskDto> createNewTask(
            @PathVariable
            Long projectId,
            @RequestBody
            CreateTaskReqDto reqDto,
            @CurrentUser User user
    ) {
        TaskDto taskDto = taskService
                .createNew(projectId, user, reqDto);

        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @Operation(summary = "Удаляет таску")
    @DeleteMapping("/{taskId}")
    ResponseEntity deleteTask(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            Principal principal
    ) {
        taskService.delete(taskId);

        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @Operation(summary = "Изменить таску")
    @PatchMapping("/{taskId}")
    ResponseEntity<TaskDto> updateTask(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @RequestBody Long taskExecutorId,
            Principal principal
    ) {
        //TODO
        return new ResponseEntity("OK", HttpStatus.OK);
    }
}
