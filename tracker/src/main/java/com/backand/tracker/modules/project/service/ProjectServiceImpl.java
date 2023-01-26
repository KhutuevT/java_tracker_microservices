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

import javax.persistence.EntityNotFoundException;

@Service
public class ProjectServiceImpl implements
        ProjectService {
    private final ProjectRepository projectRepository;
    private final UserProjectService userProjectService;
    private final UserService userService;
    private final ProjectRoleService projectRoleService;
    private final ProjectRolePermissionsService projectRolePermissionsService;

    private final ProjectMapper projectMapper;

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
        this.projectRolePermissionsService = projectRolePermissionsService;
        this.projectMapper = projectMapper;
    }

//    @Override
//    public ProjectDto createNewProject(
//            User user,
//            String name,
//            String descriptions,
//            String image
//    ) {
//        Project project = projectRepository
//                .save(new Project(name, descriptions, user, image));
//
//        ProjectRole projectRole = projectRoleService
//                .createNew(String.valueOf(ProjectPermissionsEnum.READ), user, project.getId());
//
//        projectRolePermissionsService
//                .addNewPermissionInProjectRole(user, projectRole, ProjectPermissionsEnum.READ);
//
//        return projectMapper.toDto(project);
//    }

    @Override
    public ProjectDto createNewProject(Project project) {
        Project saveProject = projectRepository.save(project);

        ProjectRole projectRole = projectRoleService
                .createNew(
                        String.valueOf(ProjectPermissionsEnum.READ),
                        saveProject.getCreator(),
                        saveProject.getId());

        projectRolePermissionsService.
                addNewPermissionInProjectRole(
                        saveProject.getCreator(),
                        projectRole,
                        ProjectPermissionsEnum.READ);

        return projectMapper.toDto(saveProject);
    }

    @Override
    public ProjectDto getById(
            User user,
            Long id
    ) {
        Project project = getById(id);
        UserPermissionsCheck
                .checkUserPermissionInProjectWithException(
                        user,
                        project,
                        ProjectPermissionsEnum.READ
                );
        return projectMapper.toDto(project);
    }

    @Override
    public void deleteProject(
            User user,
            Long projectId
    ) {
        Project project = getById(projectId);
        UserPermissionsCheck
                .checkUserPermissionInProjectWithException(
                        user,
                        project,
                        ProjectPermissionsEnum.DELETE
                );

        projectRepository.delete(project);
    }

    @Override
    public void addEmployeeInProject(
            User user,
            Long projectId,
            Long employeeUserId
    ) {
        User employee = userService.getUser(
                employeeUserId);

        Project project = getById(projectId);
        UserPermissionsCheck
                .checkUserPermissionInProjectWithException(
                        user,
                        project,
                        ProjectPermissionsEnum.ADD_USER
                );

        ProjectRole onlyReadRole = projectRoleService
                .getByName(user, String.valueOf(ProjectPermissionsEnum.READ));

        UserProject userProject = userProjectService
                .createNewUserProject(employee, project, onlyReadRole);
    }

    @Override
    public void deleteEmployeeInProject(
            User user,
            Long projectId,
            Long employeeUserId
    ) {
        User employee = userService.getUser(employeeUserId);

        Project project = getById(projectId);

        UserPermissionsCheck
                .checkUserPermissionInProjectWithException(
                        user,
                        project,
                        ProjectPermissionsEnum.DELETE_USER
                );

        userProjectService
                .deleteUserProject(employee, project);
    }

    @Override
    public Project getById(Long id) {
        return projectRepository
                .getProjectById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found!"));
    }
}
