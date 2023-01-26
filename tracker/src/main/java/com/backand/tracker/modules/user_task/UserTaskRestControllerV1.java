package com.backand.tracker.modules.user_task;

import com.backand.tracker.modules.user_task.services.UserTaskService;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/projects/{projectId}/tasks/{taskID}/user-tasks")
public class UserTaskRestControllerV1 {

    private UserRepository userRepository;
    private UserTaskService userTaskService;

    @Autowired
    public UserTaskRestControllerV1(
            UserRepository userRepository,
            UserTaskService userTaskService
    ) {
        this.userRepository = userRepository;
        this.userTaskService = userTaskService;
    }

    @GetMapping()
    public ResponseEntity get(
            @PathVariable String projectId,
            @PathVariable String taskID,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return null;
    }

    @GetMapping("/{userTaskId}")
    public ResponseEntity getById(
            @PathVariable String projectId,
            @PathVariable String taskID,
            @PathVariable String userTaskId,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return null;
    }
}
