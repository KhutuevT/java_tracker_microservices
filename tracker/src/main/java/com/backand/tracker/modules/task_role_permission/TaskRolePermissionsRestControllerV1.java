package com.backand.tracker.modules.task_role_permission;

import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user.UserRepository;
import com.backand.tracker.modules.task_role_permission.services.TaskRolePermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/projects/{projectId}/tasks/{taskId}/roles/{roleId}/permissions")
public class TaskRolePermissionsRestControllerV1 {

    private final UserRepository userRepository;
    private final TaskRolePermissionsService taskRolePermissionsService;

    @Autowired
    public TaskRolePermissionsRestControllerV1(
            UserRepository userRepository,
            TaskRolePermissionsService taskRolePermissionsService
    ) {
        this.userRepository = userRepository;
        this.taskRolePermissionsService = taskRolePermissionsService;
    }

    @GetMapping()
    public ResponseEntity getTaskRolesPermissions(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @PathVariable Long roleId,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).get();
       return null;
    }

    @GetMapping("/{permissionsId}")
    public ResponseEntity getTaskRolesPermissionsById(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @PathVariable Long roleId,
            @PathVariable Long permissionsId,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return null;
    }

}
