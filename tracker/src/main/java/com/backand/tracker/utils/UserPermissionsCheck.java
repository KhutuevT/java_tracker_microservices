package com.backand.tracker.utils;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project_role_permission.ProjectPermissionsEnum;
import com.backand.tracker.modules.project_role_permission.ProjectRolePermissions;
import com.backand.tracker.modules.user_project.UserProject;
import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.task_role_permission.TaskPermissionsEnum;
import com.backand.tracker.modules.task_role_permission.TaskRolePermissions;
import com.backand.tracker.modules.user_task.UserTask;
import com.backand.tracker.modules.user.User;
import org.springframework.security.access.AccessDeniedException;

import java.util.Collection;
import java.util.Objects;

public class UserPermissionsCheck {
    public static boolean checkUserPermissionsInProject(
            User user,
            Project project,
            ProjectPermissionsEnum projectPermission
    ) {
        boolean userIsCreator = Objects.equals(project.getCreator().getId(), user.getId());
        //Если пользователь создатель проекта - то у него есть все права
        if (userIsCreator) {
            return true;
        }

        Collection<UserProject> userProjects = project.getUserProjects();

        for (UserProject userProject : userProjects) {
            if (Objects.equals(userProject.getUser().getId(), user.getId())) {
                Collection<ProjectRolePermissions> projectRolePermissions =
                        userProject.getProjectRole().getProjectRolePermissions();

                for (ProjectRolePermissions projectRolePermission : projectRolePermissions) {
                    if (projectRolePermission.getProjectPermission() == projectPermission) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    public static boolean checkUserPermissionInTask(
            User user,
            Task task,
            TaskPermissionsEnum taskPermission
    ) {
        boolean userIsCreator = Objects.equals(task.getCreator().getId(), user.getId());

        if (userIsCreator) {
            return true;
        }

        Collection<UserTask> userTasks = task.getUserTasks();

        for (UserTask userTask : userTasks) {
            if (Objects.equals(userTask.getUser().getId(), user.getId())) {
                Collection<TaskRolePermissions> taskRolePermissions = userTask.getTaskRole().getTaskRolePermissions();

                for (TaskRolePermissions taskRolePermission : taskRolePermissions) {
                    if (taskRolePermission.getTaskPermissions() == taskPermission) {
                        return true;
                    }
                }

                return false;
            }
        }
        return false;
    }

    public static void checkUserPermissionInProjectWithException(
            User user,
            Project project,
            ProjectPermissionsEnum projectPermission
    ) {
        if (!checkUserPermissionsInProject(user, project, projectPermission)) {
            throw new AccessDeniedException("Access denied!");
        }
    }

    public static void checkUserPermissionInTaskWithException(
            User user,
            Task task,
            TaskPermissionsEnum taskPermission
    ) {
        if (!checkUserPermissionInTask(user, task, taskPermission)) {
            throw new AccessDeniedException("Access denied!");
        }
    }
}
