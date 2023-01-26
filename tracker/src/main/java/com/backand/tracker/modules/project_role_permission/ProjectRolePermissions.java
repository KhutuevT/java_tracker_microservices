package com.backand.tracker.modules.project_role_permission;

import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.utils.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "project_role_permissions")
public class ProjectRolePermissions extends AbstractBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_role_id")
    private ProjectRole projectRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "permission")
    private ProjectPermissionsEnum projectPermission;

    @Deprecated
    public ProjectRolePermissions() {
    }

    public ProjectRolePermissions(
            ProjectRole projectRole,
            ProjectPermissionsEnum projectPermission
    ) {
        this.projectRole = projectRole;
        this.projectPermission = projectPermission;
    }
}
