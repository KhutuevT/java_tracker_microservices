package com.backand.tracker.modules.task_role.services;

import com.backand.tracker.modules.task_role.TaskRole;
import com.backand.tracker.modules.user.User;

import java.util.Collection;

public interface TaskRoleService {
    TaskRole createNew(String name, User creator, Long id, Long projectId, Long taskId);

    void delete(User user, Long id, Long projectId, Long taskId, Long taskRoleId);

    Collection<TaskRole> getAllByTask(User user, Long taskId);

    TaskRole getById(User user, Long id, Long taskId);
}
