package com.backand.tracker.modules.task;

import com.backand.tracker.modules.task_role.TaskRole;
import com.backand.tracker.modules.time_slice.TimeSlice;
import com.backand.tracker.modules.user_task.UserTask;
import com.backand.tracker.utils.AbstractBaseEntity;
import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tasks")
@Setter
@Getter
public class Task extends AbstractBaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User creator;

    @ToString.Exclude
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private List<TimeSlice> timeSlices = new ArrayList();

    @ToString.Exclude
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private Collection<UserTask> userTasks = new ArrayList();

    @ToString.Exclude
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private Collection<TaskRole> taskRoles = new ArrayList();

    @Deprecated
    public Task() {
    }

    public Task(
            String name,
            String description,
            Project project,
            User creator
    ) {
        this.name = name;
        this.description = description;
        this.project = project;
        this.creator = creator;
    }
}
