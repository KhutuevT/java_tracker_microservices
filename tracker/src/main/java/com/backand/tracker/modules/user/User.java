package com.backand.tracker.modules.user;

import com.backand.tracker.utils.AbstractBaseEntity;
import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.modules.user.primitives.EmailAddress;
import com.backand.tracker.modules.user.primitives.Password;
import com.backand.tracker.modules.user_project.UserProject;
import com.backand.tracker.modules.task_role.TaskRole;
import com.backand.tracker.modules.time_slice.TimeSlice;
import com.backand.tracker.modules.user_task.UserTask;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User extends AbstractBaseEntity {
    @Column(name = "username")
    private String username;

    @Column(name = "avatar")
    private String avatar;

    @Embedded
    @AttributeOverride(name = "emailAddress", column = @Column(name = "email"))
    private EmailAddress emailAddress;

    @Embedded
    @AttributeOverride(name = "password", column = @Column(name = "password"))
    private Password password;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<ProjectRole> createdProjectRoles;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<TaskRole> createdTaskRoles;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<UserTask> userTasks;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<UserProject> userProjects;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<TimeSlice> timeSlices;

    public static class Builder {
        private String username;
        private EmailAddress emailAddress;
        private Password password;
        private String avatar = "default-user-avatar.png";

        public Builder(String username, EmailAddress emailAddress, Password password) {
            this.username = username;
            this.emailAddress = emailAddress;
            this.password = password;
        }

        public Builder avatar(String val) {
            avatar = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Deprecated
    public User() {
    }

    private User(Builder builder) {
        username = builder.username;
        emailAddress = builder.emailAddress;
        password = builder.password;
        avatar = builder.avatar;
    }
}
