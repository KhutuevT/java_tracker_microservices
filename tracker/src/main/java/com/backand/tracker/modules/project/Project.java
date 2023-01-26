package com.backand.tracker.modules.project;

import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.modules.user_project.UserProject;
import com.backand.tracker.utils.AbstractBaseEntity;
import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "projects")
@Setter
@Getter
public class Project extends AbstractBaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "descriptions")
    private String descriptions;

    @Column(name = "image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User creator;

    @ToString.Exclude
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private Collection<Task> tasks = new ArrayList();

    @ToString.Exclude
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private Collection<UserProject> userProjects = new ArrayList();

    @ToString.Exclude
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private Collection<ProjectRole> projectRoles = new ArrayList();

    public static class Builder {
        private String name;
        private User creator;

        private String descriptions = "";
        private String image = "default-project-image.png";

        public Builder(String name, User creator) {
            this.name = name;
            this.creator = creator;
        }

        public Builder descriptions(String val) {
            descriptions = val;
            return this;
        }

        public Builder image(String val) {
            image = val;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }

    @Deprecated
    public Project() {
    }

    private Project(Builder builder) {
        name = builder.name;
        descriptions = builder.descriptions;
        creator = builder.creator;
        image = builder.image;
    }
}
