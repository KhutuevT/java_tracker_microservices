package com.backand.tracker.modules.time_slice;

import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.time_slice.TimeSlice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public interface TimeSliceRepository extends JpaRepository<TimeSlice, Long> {
//    Optional<TimeSlice> findFirstByTaskOrderByStartTimePointDesc(Task task);

    Collection<TimeSlice> findAllByTaskId(Long taskId);
//    Collection<TimeSlice> findAllByTaskIdAndDateBetween(Long taskId, Date start, Date end);
//    Collection<TimeSlice> findTopByTaskId(Long taskId);
}

