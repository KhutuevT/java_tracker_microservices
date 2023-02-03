package com.backand.tracker.modules.project.service;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project.ProjectMapper;
import com.backand.tracker.modules.project.ProjectRepository;
import com.backand.tracker.modules.project.dto.res.ProjectDto;
import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.modules.project_role.services.ProjectRoleService;
import com.backand.tracker.modules.project_role_permission.services.ProjectRolePermissionsService;
import com.backand.tracker.modules.user_project.services.UserProjectService;
import com.backand.tracker.modules.project_role_permission.ProjectPermissionsEnum;
import com.backand.tracker.modules.user_project.UserProject;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user.services.UserService;
import com.backand.tracker.utils.UserPermissionsCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectServiceImpl implements
        ProjectService {
    private final ProjectRepository projectRepository;
    private final UserProjectService userProjectService;
    private final UserService userService;
    private final ProjectRoleService projectRoleService;

    @Autowired
    public ProjectServiceImpl(
            ProjectRepository projectRepository,
            UserProjectService userProjectService,
            UserService userService,
            @Lazy ProjectRoleService projectRoleService,
            ProjectRolePermissionsService projectRolePermissionsService,
            ProjectMapper projectMapper
    ) {
        this.projectRepository = projectRepository;
        this.userProjectService = userProjectService;
        this.userService = userService;
        this.projectRoleService = projectRoleService;
    }

    @Override
    public Project createNewProject(Project project) {
        Project saveProject = projectRepository.save(project);

        projectRoleService
                .createNew(
                        String.valueOf(ProjectPermissionsEnum.READ),
                        saveProject.getCreator(),
                        saveProject.getId());
        return saveProject;
    }

    @Override
    public void deleteProject(
            Long projectId
    ) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public Project getById(Long id) {
        return projectRepository
                .getProjectById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found!"));
    }
}
