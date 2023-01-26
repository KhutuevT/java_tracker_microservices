package com.backand.tracker.modules.user_project;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.utils.AbstractBaseEntity;
import com.backand.tracker.modules.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Описывает проекты юзера
 * (созданные им или если его добавили в проект)
 */
@Setter
@Getter
@Table(name = "user_projects")
@Entity
public class UserProject extends AbstractBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_role_id")
    private ProjectRole projectRole;

    @Deprecated
    public UserProject() {
    }

    public UserProject(
            User user,
            Project project,
            ProjectRole projectRole
    ) {
        this.user = user;
        this.project = project;
        this.projectRole = projectRole;
    }
}
