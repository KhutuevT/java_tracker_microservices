package com.backand.tracker.modules.project_role;

import com.backand.tracker.modules.project_role.services.ProjectRoleService;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.project_role.dto.req.CreateProjectRoleReqDto;
import com.backand.tracker.modules.user.UserRepository;
import com.backand.tracker.modules.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/projects/{projectId}/roles")
public class ProjectRoleRestControllerV1 {

    private ProjectRoleService projectRoleService;
    private UserService userService;

    @Autowired
    public ProjectRoleRestControllerV1(
            ProjectRoleService projectRoleService,
            UserService userService
    ) {
        this.userService = userService;
        this.projectRoleService = projectRoleService;
    }

    @GetMapping()
    public ResponseEntity get(
            @PathVariable Long projectId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Collection<ProjectRole> projectRole = projectRoleService.getAllByProject(user, projectId);
        return new ResponseEntity(projectRole, HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity getById(
            @PathVariable Long projectId,
            @PathVariable Long roleId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        return null;
    }

    @PostMapping()
    public ResponseEntity addNew(
            @PathVariable Long projectId,
            @RequestBody CreateProjectRoleReqDto reqDto,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        ProjectRole projectRole = projectRoleService.createNew(reqDto.getName(), user, projectId);
        return new ResponseEntity(projectRole, HttpStatus.OK);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity deleteById(
            @PathVariable Long projectId,
            @PathVariable Long roleId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        return null;
    }
}
