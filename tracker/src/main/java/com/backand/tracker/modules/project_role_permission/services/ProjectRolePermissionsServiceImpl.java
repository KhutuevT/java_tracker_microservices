package com.backand.tracker.modules.project_role_permission.services;

import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.modules.project_role_permission.ProjectPermissionsEnum;
import com.backand.tracker.modules.project_role_permission.ProjectRolePermissions;
import com.backand.tracker.modules.project_role_permission.ProjectRolePermissionsRepository;
import com.backand.tracker.modules.project_role_permission.services.ProjectRolePermissionsService;
import com.backand.tracker.modules.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectRolePermissionsServiceImpl implements ProjectRolePermissionsService {

    private final ProjectRolePermissionsRepository projectRolePermissionsRepository;

    @Autowired
    public ProjectRolePermissionsServiceImpl(
            ProjectRolePermissionsRepository projectRolePermissionsRepository
    ) {
        this.projectRolePermissionsRepository = projectRolePermissionsRepository;
    }

    @Override
    public void addNewPermissionInProjectRole(
            User user,
            ProjectRole projectRole,
            ProjectPermissionsEnum projectPermissions
    ) {
        ProjectRolePermissions permission = new ProjectRolePermissions(
                projectRole,
                projectPermissions
        );
        projectRolePermissionsRepository.save(permission);
    }
}
