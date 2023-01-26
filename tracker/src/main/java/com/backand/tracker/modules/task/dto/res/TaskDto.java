package com.backand.tracker.modules.task.dto.res;

import com.backand.tracker.modules.task_role.dto.res.TaskRoleDto;
import com.backand.tracker.modules.time_slice.dto.res.TimeSliceDto;
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
public class TaskDto extends AbstractDto {
    private String name;
    private String description;
    private Long projectId;
    private Long creatorId;
    private Collection<TimeSliceDto> timeSlices;
    private Collection<UserTaskDto> userTasks;
    private Collection<TaskRoleDto> taskRoles;
}
