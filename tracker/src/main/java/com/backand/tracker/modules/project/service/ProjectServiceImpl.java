package com.backand.tracker.modules.project.service;

import com.backand.tracker.annotations.CheckProjectPermissions;
import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project.ProjectMapper;
import com.backand.tracker.modules.project.ProjectRepository;
import com.backand.tracker.modules.project.dto.req.CreateProjectReqDto;
import com.backand.tracker.modules.project.dto.req.UpdateProjectReqDto;
import com.backand.tracker.modules.project.dto.res.ProjectDto;
import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.modules.project_role.ProjectRoleRepository;
import com.backand.tracker.modules.project_role.services.ProjectRoleService;
import com.backand.tracker.modules.project_role_permission.services.ProjectRolePermissionsService;
import com.backand.tracker.modules.user_project.services.UserProjectService;
import com.backand.tracker.modules.project_role_permission.ProjectPermission;
import com.backand.tracker.modules.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectRoleService projectRoleService;
    private final ProjectMapper projectMapper;
    private final ProjectRoleRepository projectRoleRepository;
    private final UserProjectService userProjectService;
    private final ProjectRolePermissionsService projectRolePermissionsService;

    @Autowired
    public ProjectServiceImpl(
            ProjectRepository projectRepository,
            @Lazy ProjectRoleService projectRoleService,
            ProjectMapper projectMapper,
            ProjectRoleRepository projectRoleRepository,
            UserProjectService userProjectService, ProjectRolePermissionsService projectRolePermissionsService) {
        this.projectRepository = projectRepository;
        this.projectRoleService = projectRoleService;
        this.projectMapper = projectMapper;
        this.projectRoleRepository = projectRoleRepository;
        this.userProjectService = userProjectService;
        this.projectRolePermissionsService = projectRolePermissionsService;
    }

    @Transactional
    @Override
    public ProjectDto createNew(CreateProjectReqDto createProjectReqDto, User user) {
        Project project = new Project.Builder(createProjectReqDto.getName(), user)
                .descriptions(createProjectReqDto.getDescriptions())
                .build();
        Project saveProject = projectRepository.save(project);

        //?????? ???????????????? ?????????????? ?????????????????? ?????????????? ???????? ?????? ????????????
        ProjectRole onlyReadRole = projectRoleService
                .createNew("ONLY READ",
                        user,
                        saveProject.getId());

        projectRolePermissionsService.addNewPermissionInProjectRole(onlyReadRole, ProjectPermission.READ);

        //?? ?????? ???? ???????? ???????????????????????????? ??????????????
        ProjectRole adminProjectRole = projectRoleService
                .createNew("PROJECT ADMIN",
                        user,
                        saveProject.getId());

        projectRolePermissionsService.addNewPermissionInProjectRole(adminProjectRole, ProjectPermission.ADMIN);

        userProjectService
                .createNewUserProject(user,
                        saveProject,
                        adminProjectRole);

        return projectMapper.toDto(saveProject);
    }

    @CheckProjectPermissions(permission = ProjectPermission.UPDATE)
    @Override
    public ProjectDto updateAvatar(Long projectId, User user, String avatarName) {
        Project project = getById(projectId);
        project.setImage(avatarName);
        Project updatedProject = projectRepository.save(project);
        return projectMapper.toDto(updatedProject);
    }

    @CheckProjectPermissions(permission = ProjectPermission.DELETE)
    @Override
    public void delete(
            Long projectId
    ) {
        projectRepository.deleteById(projectId);
    }

    @CheckProjectPermissions(permission = ProjectPermission.UPDATE)
    @Override
    public ProjectDto update(Long projectId, UpdateProjectReqDto updateProjectReqDto, User user) {
        return null;
    }


    @CheckProjectPermissions(permission = ProjectPermission.READ)
    @Override
    public ProjectDto getDtoById(Long id) {
        return projectMapper.toDto(getById(id));
    }

    @CheckProjectPermissions(permission = ProjectPermission.READ)
    @Override
    public Project getById(Long id) {
        return projectRepository.getProjectById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found!"));
    }
}
