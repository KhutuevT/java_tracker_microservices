package com.backand.tracker.modules.project.dto.res;

import com.backand.tracker.modules.project_role.dto.res.ProjectRoleDto;
import com.backand.tracker.modules.task.dto.res.TaskDto;
import com.backand.tracker.modules.user_project.dto.res.UserProjectDto;
import com.backand.tracker.utils.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto extends AbstractDto {
    private String name;
    private String descriptions;
    private Long creatorId;
    private Collection<TaskDto> tasks;
    private Collection<UserProjectDto> userProjects;
    private Collection<ProjectRoleDto> projectRoles;
}
