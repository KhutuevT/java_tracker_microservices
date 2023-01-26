package com.backand.tracker.modules.project_role_permission.services;

import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.modules.project_role_permission.ProjectPermissionsEnum;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user_project.UserProject;

public interface ProjectRolePermissionsService {
    void addNewPermissionInProjectRole(User user, ProjectRole projectRole, ProjectPermissionsEnum projectPermissions);
}
