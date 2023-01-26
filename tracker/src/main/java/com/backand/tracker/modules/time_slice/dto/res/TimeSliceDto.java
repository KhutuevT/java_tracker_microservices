package com.backand.tracker.modules.time_slice.dto.res;

import com.backand.tracker.modules.time_slice.primitives.TimePoint;
import com.backand.tracker.modules.user.dto.res.UserDto;
import com.backand.tracker.utils.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSliceDto extends AbstractDto {
    private String name;
    private TimePoint startTimePoint;
    private TimePoint endTimePoint;
    private Long taskId;
    private Long userId;
}
