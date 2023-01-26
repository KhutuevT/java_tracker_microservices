package com.backand.tracker.modules.time_slice.services;

import com.backand.tracker.modules.time_slice.primitives.TimePoint;
import com.backand.tracker.modules.time_slice.TimeSlice;
import com.backand.tracker.modules.user.User;

import java.util.List;

public interface TimeSliceService {
    TimeSlice getById(Long id);

    List<TimeSlice> getTaskTimeSlices(Long taskId);

    List<TimeSlice> getTaskTimeSlices(Long taskId, TimePoint startDate, TimePoint endDate);

    List<TimeSlice> getTaskTimeSlicesByUser(Long taskId, Long userId);

    List<TimeSlice> getTaskTimeSlicesByUser(Long taskId, Long userId, TimePoint startDate, TimePoint endDate);

    TimeSlice start(User user, Long projectId, Long taskId, String name);
    TimeSlice stop(User user, Long projectId, Long taskId);
}
