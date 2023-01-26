package com.backand.tracker.modules.time_slice.primitives;

import lombok.Data;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@Data
public class TimePoint {
    Date timePoint;

    public TimePoint() {
        timePoint = new Date();
    }

    public TimePoint(Date time) {
        this.timePoint = time;
    }

    @Override
    public boolean equals(Object obj) {
        //TODO
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(timePoint);
    }
}
