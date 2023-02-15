package com.backand.tracker.modules.project_role.services;

import com.backand.tracker.modules.project_role.ProjectRoleRepository;
import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project_role_permission.ProjectPermission;
import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.project.service.ProjectService;
import com.backand.tracker.utils.UserPermissionsCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
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
        Project project = projectService.getProjectById(projectId);

        ProjectRole projectRole = new ProjectRole(name, creator, project);
        return projectRoleRepository.save(projectRole);
    }

    @Override
    public void delete(Long id, Long projectId) {
        Project project = projectService.getProjectById(projectId);
        projectRoleRepository.deleteById(id);
    }

    @Override
    public Collection<ProjectRole> getAllByProjectId(Long projectId) {
        Project project = projectService.getProjectById(projectId);
        return projectRoleRepository.findProjectRolesByProjectId(projectId);
    }

    @Override
    public ProjectRole getById(Long id, Long projectId) {
        Project project = projectService.getProjectById(projectId);
        return projectRoleRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found!"));
    }

    @Override
    public ProjectRole getByName(String name) {
        return projectRoleRepository
                .getProjectRoleByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Project role not found!"));
    }
}
