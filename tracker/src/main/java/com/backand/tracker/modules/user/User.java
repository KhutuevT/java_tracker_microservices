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

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User extends AbstractBaseEntity implements UserDetails {
    @Column(name = "username")
    private String username;

    @Column(name = "avatar")
    private String avatar;

    @Embedded
    @AttributeOverride(name = "emailAddress", column = @Column(name = "email"))
    private EmailAddress emailAddress;

    //@Embedded
    //@AttributeOverride(name = "password", column = @Column(name = "password"))
    @Column(name="password")
    private String password;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static class Builder {
        private String username;
        private EmailAddress emailAddress;
        private String password;
        private String avatar = "default-user-avatar.png";

        public Builder(String username, EmailAddress emailAddress, String password) {
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
