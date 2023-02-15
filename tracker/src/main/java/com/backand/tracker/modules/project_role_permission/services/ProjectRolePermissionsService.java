package com.backand.tracker.modules.project_role_permission.services;

import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.modules.project_role_permission.ProjectPermission;
import com.backand.tracker.modules.user.User;

public interface ProjectRolePermissionsService {
    void addNewPermissionInProjectRole(User user, ProjectRole projectRole, ProjectPermission projectPermissions);
}
