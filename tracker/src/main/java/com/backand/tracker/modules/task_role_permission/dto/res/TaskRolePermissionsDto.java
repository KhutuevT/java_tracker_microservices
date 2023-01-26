package com.backand.tracker.modules.task_role_permission.dto.res;

import com.backand.tracker.modules.task_role_permission.TaskPermissionsEnum;
import com.backand.tracker.utils.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRolePermissionsDto extends AbstractDto {
    private Long taskRoleId;
    private TaskPermissionsEnum taskPermissions;
}
