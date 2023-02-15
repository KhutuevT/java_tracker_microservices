package com.backand.tracker.modules.user_project.services;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.modules.user_project.UserProject;
import com.backand.tracker.modules.user.User;

public interface UserProjectService {
    UserProject getByProjectIdAndUserId(Long projectId, Long userId);
    UserProject createNewUserProject(User user, Project project, ProjectRole projectRole);

    UserProject deleteUserProject(User user, Project project);

    /**
     * Добавить работника в проект.
     */
    void addEmployeeInProject(User user, Long projectId, Long employeeUserId);

    /**
     * Удалить работника из проекта.
     */
    void deleteEmployeeInProject(User user, Long projectId, Long employeeUserId);
}
