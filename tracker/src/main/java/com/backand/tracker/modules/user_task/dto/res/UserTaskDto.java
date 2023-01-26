package com.backand.tracker.modules.user_task.dto.res;

import com.backand.tracker.utils.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTaskDto extends AbstractDto {
    private Long userId;
    private Long taskId;
    private Long taskRoleId;
}
