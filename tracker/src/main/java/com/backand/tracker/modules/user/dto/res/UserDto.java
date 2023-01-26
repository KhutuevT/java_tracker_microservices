package com.backand.tracker.modules.user.dto.res;

import com.backand.tracker.modules.project_role.dto.res.ProjectRoleDto;
import com.backand.tracker.modules.task_role.dto.res.TaskRoleDto;
import com.backand.tracker.modules.time_slice.dto.res.TimeSliceDto;
import com.backand.tracker.modules.user.primitives.EmailAddress;
import com.backand.tracker.modules.user_project.dto.res.UserProjectDto;
import com.backand.tracker.modules.user_task.dto.res.UserTaskDto;
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
public class UserDto extends AbstractDto {
    String username;
    private EmailAddress emailAddress;
    private Collection<ProjectRoleDto> createdProjectRoles;
    private Collection<TaskRoleDto> createdTaskRoles;
    private Collection<UserTaskDto> userTasks;
    private Collection<UserProjectDto> userProjects;
    private Collection<TimeSliceDto> timeSlices;
}
