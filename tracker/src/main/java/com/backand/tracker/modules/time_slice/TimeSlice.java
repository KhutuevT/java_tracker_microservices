package com.backand.tracker.modules.time_slice;

import com.backand.tracker.modules.task.Task;
import com.backand.tracker.utils.AbstractBaseEntity;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.time_slice.primitives.TimePoint;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Описывает "затреканный" промежуток времени
 * определённым юзером в определённой таске.
 */
@Entity
@Table(name = "time_slice")
@Setter
@Getter
public class TimeSlice extends AbstractBaseEntity {
    @Column(name = "name")
    private String name;

    @Embedded
    @AttributeOverride(name = "timePoint", column = @Column(name = "start_time_point"))
    @Temporal(TemporalType.TIMESTAMP)
    private TimePoint startTimePoint;

    @Embedded
    @AttributeOverride(name = "timePoint", column = @Column(name = "end_time_point"))
    @Temporal(TemporalType.TIMESTAMP)
    private TimePoint endTimePoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Deprecated
    public TimeSlice() {
    }

    public TimeSlice(
            String name,
            TimePoint startTimePoint,
            //TimePoint endTimePoint,
            Task task,
            User user
    ) {
        this.name = name;
        this.startTimePoint = startTimePoint;
        //this.endTimePoint = endTimePoint;
        this.task = task;
        this.user = user;
    }
}
