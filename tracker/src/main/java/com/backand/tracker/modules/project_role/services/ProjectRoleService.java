package com.backand.tracker.modules.project_role.services;

import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.modules.user.User;

import java.util.Collection;

public interface ProjectRoleService {
    ProjectRole createNew(String name, User creator, Long projectId);

    void delete(Long id, Long projectId);

    Collection<ProjectRole> getAllByProjectId(Long projectId);

    ProjectRole getById(Long id, Long projectId);

    ProjectRole getByName(String name);
}
