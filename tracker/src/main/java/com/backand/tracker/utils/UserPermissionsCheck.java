package com.backand.tracker.utils;

import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.modules.project_role.services.ProjectRoleService;
import com.backand.tracker.modules.project_role_permission.ProjectPermission;
import com.backand.tracker.modules.project_role_permission.ProjectRolePermissions;
import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.task_role_permission.TaskPermission;
import com.backand.tracker.modules.task_role_permission.TaskRolePermissions;
import com.backand.tracker.modules.user_project.UserProject;
import com.backand.tracker.modules.user_project.services.UserProjectService;
import com.backand.tracker.modules.user_task.UserTask;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user_task.services.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Service
public class UserPermissionsCheck {

    private static UserProjectService userProjectService;
    public static UserTaskService userTaskService;

    @Autowired
    public UserPermissionsCheck(
            UserProjectService userProjectService,
            UserTaskService userTaskService
    ) {
        this.userProjectService = userProjectService;
        this.userTaskService = userTaskService;
    }

    public static boolean inProject(
            User user,
            Long projectId,
            ProjectPermission projectPermission
    ) {
        UserProject userProject = userProjectService.getByProjectIdAndUserId(projectId, user.getId());
        for (ProjectRolePermissions projectRolePermission : userProject.getProjectRole().getProjectRolePermissions()){
            if (projectRolePermission.getProjectPermission() == projectPermission){
                return true;
            }
        }
        return false;
    }

    public static boolean inTask(User user, Long taskId, TaskPermission taskPermission) {
        UserTask userTask = userTaskService.getByTaskIdAndUserId(taskId, user.getId());
        for (TaskRolePermissions taskRolePermissions : userTask.getTaskRole().getTaskRolePermissions()) {
            if (taskRolePermissions.getTaskPermissions() == taskPermission) {
                return true;
            }
        }
        return false;
    }

    public static void inProjectWithException(
            User user,
            Long projectId,
            ProjectPermission projectPermission
    ) {
        if (!inProject(user, projectId, projectPermission)) {
            throw new AccessDeniedException("Access denied!");
        }
    }

    public static void inTaskWithException(
            User user,
            Long taskId,
            TaskPermission taskPermission
    ) {
        if (!inTask(user, taskId, taskPermission)) {
            throw new AccessDeniedException("Access denied!");
        }
    }
}
