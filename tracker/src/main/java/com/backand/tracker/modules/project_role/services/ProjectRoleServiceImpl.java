package com.backand.tracker.modules.project_role.services;

import com.backand.tracker.modules.project_role.ProjectRoleRepository;
import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project_role_permission.ProjectPermissionsEnum;
import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.project_role.services.ProjectRoleService;
import com.backand.tracker.modules.project.service.ProjectService;
import com.backand.tracker.utils.UserPermissionsCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class ProjectRoleServiceImpl implements ProjectRoleService {

    private ProjectRoleRepository projectRoleRepository;
    private ProjectService projectService;

    @Autowired
    public ProjectRoleServiceImpl(
            ProjectRoleRepository projectRoleRepository,
            ProjectService projectService
    ) {
        this.projectRoleRepository = projectRoleRepository;
        this.projectService = projectService;
    }

    @Override
    public ProjectRole createNew(String name, User creator, Long projectId) {
        Project project = projectService.getById(projectId);
        UserPermissionsCheck
                .checkUserPermissionInProjectWithException(
                        creator,
                        project,
                        ProjectPermissionsEnum.CREATE_ROLE
                );

        ProjectRole projectRole = new ProjectRole(name, creator, project);
        return projectRoleRepository.save(projectRole);
    }

    @Override
    public void delete(User user, Long id, Long projectId) {
        Project project = projectService.getById(projectId);
        UserPermissionsCheck
                .checkUserPermissionInProjectWithException(
                        user,
                        project,
                        ProjectPermissionsEnum.DELETE_ROLE
                );
        projectRoleRepository.deleteById(id);
    }

    @Override
    public Collection<ProjectRole> getAllByProject(User user, Long projectId) {
        Project project = projectService.getById(projectId);
        UserPermissionsCheck
                .checkUserPermissionInProjectWithException(
                        user,
                        project,
                        ProjectPermissionsEnum.READ
                );
        return projectRoleRepository.findProjectRolesByProjectId(projectId);
    }

    @Override
    public ProjectRole getById(User user, Long id, Long projectId) {
        Project project = projectService.getById(projectId);
        UserPermissionsCheck
                .checkUserPermissionInProjectWithException(
                        user,
                        project,
                        ProjectPermissionsEnum.READ
                );
        return projectRoleRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found!"));
    }

    @Override
    public ProjectRole getByName(User user, String name) {
        return projectRoleRepository
                .getProjectRoleByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Project role not found!"));
    }
}
