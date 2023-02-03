package com.backand.tracker.modules.project.service;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project.dto.res.ProjectDto;
import com.backand.tracker.modules.user.User;

public interface ProjectService {
    /**
     * Создаёт новый проект.
     */
    Project createNewProject(Project project);

    /**
     * Удаляет проект
     */
    void deleteProject(Long projectId);

    Project getById(Long id);
}
