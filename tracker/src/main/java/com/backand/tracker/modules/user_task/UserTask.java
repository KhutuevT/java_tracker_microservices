package com.backand.tracker.modules.user_task;

import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.task_role.TaskRole;
import com.backand.tracker.utils.AbstractBaseEntity;
import com.backand.tracker.modules.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Описывает таски юзера
 * (созданные им, или если на него повесили таску)
 */
@Setter
@Getter
@Table(name = "user_tasks")
@Entity
public class UserTask extends AbstractBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_role_id")
    private TaskRole taskRole;

    @Deprecated
    public UserTask() {
    }

    public UserTask(
            User user,
            Task task,
            TaskRole taskRole
    ) {
        this.user = user;
        this.task = task;
        this.taskRole = taskRole;
    }
}
