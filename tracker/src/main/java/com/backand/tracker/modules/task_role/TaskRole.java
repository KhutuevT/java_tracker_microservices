package com.backand.tracker.modules.task_role;

import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.task_role_permission.TaskRolePermissions;
import com.backand.tracker.modules.user_task.UserTask;
import com.backand.tracker.utils.AbstractBaseEntity;
import com.backand.tracker.modules.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

/**
 * Описывает роли которые могут быть у пользователей в таске
 */
@Setter
@Getter
@Table(name = "task_roles")
@Entity
public class TaskRole extends AbstractBaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @JsonIgnore
    @OneToMany(mappedBy = "taskRole", fetch = FetchType.LAZY)
    private Collection<TaskRolePermissions> taskRolePermissions;

    @JsonIgnore
    @OneToMany(mappedBy = "taskRole", fetch = FetchType.LAZY)
    private Collection<UserTask> userTasks;

    @Deprecated
    public TaskRole() {
    }

    public TaskRole(
            String name,
            User creator,
            Task task
    ) {
        this.name = name;
        this.creator = creator;
        this.task = task;
    }

}
