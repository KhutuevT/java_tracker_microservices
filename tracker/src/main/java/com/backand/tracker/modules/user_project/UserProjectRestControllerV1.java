package com.backand.tracker.modules.user_project;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project.service.ProjectService;
import com.backand.tracker.modules.project_role_permission.ProjectPermissionsEnum;
import com.backand.tracker.modules.user.UserRepository;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user.services.UserService;
import com.backand.tracker.modules.user_project.services.UserProjectService;
import com.backand.tracker.utils.UserPermissionsCheck;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/projects/{projectId}/user-projects")
public class UserProjectRestControllerV1 {

    private final UserProjectService userProjectService;
    private final UserService userService;
    private final ProjectService projectService;

    private final UserProjectMapper userProjectMapper;

    @Autowired
    public UserProjectRestControllerV1(
            UserProjectService userProjectService,
            ProjectService projectService,
            UserService userService,
            UserProjectMapper userProjectMapper
    ) {
        this.projectService = projectService;
        this.userService = userService;
        this.userProjectService = userProjectService;
        this.userProjectMapper = userProjectMapper;
    }

    @Operation(summary = "Возвращает пользователей в проекте по projectId")
    @GetMapping()
    public ResponseEntity get(
            @PathVariable String projectId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        return null;
    }

    @Operation(summary = "Возвращает пользователя в проекте по userProjectId")
    @GetMapping("/{userProjectId}")
    public ResponseEntity getById(
            @PathVariable String projectId,
            @PathVariable String userProjectId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        return null;
    }

    @Operation(summary = "Добавить пользователя в проект")
    @PostMapping()
    public ResponseEntity addUserInProject(
            @PathVariable Long projectId,
            @RequestBody Long employeeUserId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getById(projectId);

        UserPermissionsCheck.checkUserPermissionInProjectWithException(user, project, ProjectPermissionsEnum.ADD_USER);

        userProjectService.addEmployeeInProject(user, projectId, employeeUserId);

        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @Operation(summary = "Удаляет пользователя из проекта")
    @DeleteMapping()
    public ResponseEntity deleteUserInProject(
            @PathVariable Long projectId,
            @RequestBody Long employeeUserId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getById(projectId);

        UserPermissionsCheck.checkUserPermissionInProjectWithException(user, project, ProjectPermissionsEnum.DELETE_USER);

        userProjectService.deleteEmployeeInProject(user, projectId, employeeUserId);

        return new ResponseEntity("OK", HttpStatus.OK);
    }
}
