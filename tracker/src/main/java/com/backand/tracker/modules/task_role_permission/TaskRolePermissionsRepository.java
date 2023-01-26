package com.backand.tracker.modules.task_role_permission;

import com.backand.tracker.modules.task_role_permission.TaskRolePermissions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRolePermissionsRepository extends JpaRepository<TaskRolePermissions, Long> {
}
