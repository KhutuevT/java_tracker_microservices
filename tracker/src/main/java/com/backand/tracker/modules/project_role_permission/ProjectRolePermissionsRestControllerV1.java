package com.backand.tracker.modules.project_role_permission;

import com.backand.tracker.modules.project_role_permission.services.ProjectRolePermissionsService;
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
@RequestMapping(value = "/api/v1/projects/{projectId}/roles/{roleId}/permissions")
public class ProjectRolePermissionsRestControllerV1 {

    private UserRepository userRepository;
    private ProjectRolePermissionsService projectRolePermissionsService;

    @Autowired
    public ProjectRolePermissionsRestControllerV1(
            UserRepository userRepository,
            ProjectRolePermissionsService projectRolePermissionsService
    ) {
        this.userRepository = userRepository;
        this.projectRolePermissionsService = projectRolePermissionsService;
    }

    @GetMapping()
    public ResponseEntity getProjectRolesPermissions(
            @PathVariable Long projectId,
            @PathVariable Long roleId,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return null;
    }

    @GetMapping("/{permissionsId}")
    public ResponseEntity getProjectRolesPermissionsById(
            @PathVariable Long projectId,
            @PathVariable Long roleId,
            @PathVariable Long permissionsId,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return null;
    }
}
