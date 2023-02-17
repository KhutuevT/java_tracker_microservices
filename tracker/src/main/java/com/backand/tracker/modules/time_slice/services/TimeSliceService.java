package com.backand.tracker.modules.time_slice.services;

import com.backand.tracker.modules.time_slice.dto.res.TimeSliceDto;
import com.backand.tracker.modules.time_slice.primitives.TimePoint;
import com.backand.tracker.modules.time_slice.TimeSlice;
import com.backand.tracker.modules.user.User;

import java.sql.Time;
import java.util.List;

public interface TimeSliceService {
    TimeSliceDto getDtoById(Long timeSliceId);
    TimeSlice getById(Long timeSliceId);

    List<TimeSliceDto> getAllDtoByTaskId(Long taskId, TimePoint startDate, TimePoint endDate);
    List<TimeSlice> getAllByTaskId(Long taskId, TimePoint startDate, TimePoint endDate);

    List<TimeSliceDto> getAllDtoByTaskIdAndUserId(Long taskId, Long userId, TimePoint startDate, TimePoint endDate);
    List<TimeSlice> getAllByTaskIdAndUserId(Long taskId, Long userId, TimePoint startDate, TimePoint endDate);

    List<TimeSliceDto> getAllDtoByProjectIdAndUserId(Long projectId, Long userId, TimePoint startDate, TimePoint endDate);
    List<TimeSlice> getAllByProjectIdAndUserId(Long projectId, Long userId, TimePoint startDate, TimePoint endDate);

    TimeSliceDto start(Long taskId, User user, String name);
    TimeSliceDto stop(Long taskId, User user);

    TimeSlice getLastTimeSliceByTaskId(Long taskId);
}
