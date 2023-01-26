package com.backand.tracker.modules.project_role_permission;

import com.backand.tracker.modules.project_role_permission.ProjectRolePermissions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRolePermissionsRepository extends JpaRepository<ProjectRolePermissions, Long> {
}
