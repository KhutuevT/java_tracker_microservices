package com.backand.tracker.modules.task_role_permission;

import com.backand.tracker.modules.task_role.TaskRole;
import com.backand.tracker.utils.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "task_role_permissions")
public class TaskRolePermissions extends AbstractBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_role_id")
    private TaskRole taskRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "permission")
    private TaskPermissionsEnum taskPermissions;
}
