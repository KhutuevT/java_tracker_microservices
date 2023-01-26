package com.backand.tracker.modules.project.service;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project.dto.res.ProjectDto;
import com.backand.tracker.modules.user.User;

public interface ProjectService {
    /**
     * Создаёт новый проект.
     */
//    ProjectDto createNewProject(User user, String name, String descriptions, String image);
    ProjectDto createNewProject(Project project);

    /**
     * Удаляет проект
     */
    void deleteProject(User user, Long projectId);

    /**
     * Добавить работника в проект.
     */
    void addEmployeeInProject(User user, Long projectId, Long employeeUserId);

    /**
     * Удалить работника из проекта.
     */
    void deleteEmployeeInProject(User user, Long projectId, Long employeeUserId);

    ProjectDto getById(User user, Long id);

    Project getById(Long id);
}
