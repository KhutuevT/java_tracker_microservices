package com.backand.tracker.modules.project.service;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project.dto.req.CreateProjectReqDto;
import com.backand.tracker.modules.project.dto.req.UpdateProjectReqDto;
import com.backand.tracker.modules.project.dto.res.ProjectDto;
import com.backand.tracker.modules.user.User;

public interface ProjectService {
    ProjectDto createNew(CreateProjectReqDto createProjectReqDto, User user);
    ProjectDto updateAvatar(Long projectId, User user, String avatarName);
    void delete(Long projectId);
    ProjectDto update(Long projectId, UpdateProjectReqDto updateProjectReqDto, User user);
    ProjectDto getDtoById(Long projectId);
    Project getById(Long id);
}
