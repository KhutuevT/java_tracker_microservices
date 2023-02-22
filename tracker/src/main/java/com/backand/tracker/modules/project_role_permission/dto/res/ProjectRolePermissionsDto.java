package com.backand.tracker.modules.project_role_permission.dto.res;

import com.backand.tracker.modules.project_role_permission.ProjectPermission;
import com.backand.tracker.utils.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRolePermissionsDto extends AbstractDto {
    private Long id;
    private Long projectRoleId;
    private ProjectPermission projectPermission;
}
