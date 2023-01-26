package com.backand.tracker.modules.project_role.dto.res;

import com.backand.tracker.modules.project_role_permission.dto.res.ProjectRolePermissionsDto;
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
public class ProjectRoleDto extends AbstractDto {
    private String name;
    private Long creatorId;
    private Long projectId;
    private Collection<ProjectRolePermissionsDto> projectRolePermissions;
    private Collection<UserProjectDto> userProjects;
}

