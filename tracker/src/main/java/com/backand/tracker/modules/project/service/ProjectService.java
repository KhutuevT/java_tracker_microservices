package com.backand.tracker.modules.project.service;

import com.backand.tracker.annotations.CheckProjectPermissions;
import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project.dto.req.CreateProjectReqDto;
import com.backand.tracker.modules.project.dto.req.UpdateProjectReqDto;
import com.backand.tracker.modules.project.dto.res.ProjectDto;
import com.backand.tracker.modules.project_role_permission.ProjectPermission;
import com.backand.tracker.modules.user.User;
import org.springframework.security.access.prepost.PreAuthorize;

public interface ProjectService {
    ProjectDto createNewProject(CreateProjectReqDto createProjectReqDto, User user);

    ProjectDto updateProjectAvatar(Long projectId, User user, String avatarName);

    void deleteProject(Long projectId);
    ProjectDto updateProject(Long projectId, UpdateProjectReqDto updateProjectReqDto, User user);

    ProjectDto getProjectDtoById(Long projectId, User user);

    Project getProjectById(Long id);
}
