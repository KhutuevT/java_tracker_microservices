package com.backand.tracker.modules.task_role;

import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user.UserRepository;
import com.backand.tracker.modules.task_role.services.TaskRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/projects/{projectId}/tasks/{taskId}/roles")
public class TaskRoleRestControllerV1 {

    private TaskRoleService taskRoleService;
    private UserRepository userRepository;

    @Autowired
    public TaskRoleRestControllerV1(
            TaskRoleService taskRoleService,
            UserRepository userRepository
    ) {
        this.taskRoleService = taskRoleService;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public ResponseEntity get(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return null;
    }

    @GetMapping("/{roleId}")
    public ResponseEntity getById(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @PathVariable Long roleId,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return null;
    }

    @PostMapping()
    public ResponseEntity addNew(
            @PathVariable Long projectId,
            @PathVariable String taskId,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return null;
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity deleteById(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @PathVariable Long roleId,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return null;
    }
}
