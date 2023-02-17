package com.backand.tracker.modules.time_slice.services;

import com.backand.tracker.annotations.CheckProjectPermissions;
import com.backand.tracker.annotations.CheckTaskPermissions;
import com.backand.tracker.modules.project_role_permission.ProjectPermission;
import com.backand.tracker.modules.task_role_permission.TaskPermission;
import com.backand.tracker.modules.time_slice.TimeSliceMapper;
import com.backand.tracker.modules.time_slice.dto.res.TimeSliceDto;
import com.backand.tracker.modules.time_slice.primitives.TimePoint;
import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.time_slice.TimeSlice;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.exceptions.TimeSliceAlreadyStartException;
import com.backand.tracker.exceptions.TimeSliceAlreadyStopException;
import com.backand.tracker.modules.time_slice.TimeSliceRepository;
import com.backand.tracker.modules.task.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public class TimeSliceServiceImpl implements TimeSliceService {

    private final TimeSliceRepository timeSliceRepository;
    private final TaskService taskService;
    private final TimeSliceMapper timeSliceMapper;

    @Autowired
    public TimeSliceServiceImpl(
            TimeSliceRepository timeSliceRepository,
            TaskService taskService,
            TimeSliceMapper timeSliceMapper) {
        this.timeSliceRepository = timeSliceRepository;
        this.taskService = taskService;
        this.timeSliceMapper = timeSliceMapper;
    }

    @Override
    public TimeSliceDto getDtoById(Long timeSliceId) {
        return timeSliceMapper.toDto(getById(timeSliceId));
    }

    @Override
    public TimeSlice getById(Long timeSliceId) {
        return timeSliceRepository.findById(timeSliceId)
                .orElseThrow(() -> new EntityNotFoundException("Time slice not found!"));
    }

    @Override
    public List<TimeSliceDto> getAllDtoByTaskId(
            Long taskId,
            TimePoint startDate,
            TimePoint endDate
    ) {
        List<TimeSlice> timeSlices = getAllByTaskId(taskId, startDate, endDate);
        return timeSlices.stream().map(timeSliceMapper::toDto).toList();
    }

    @CheckTaskPermissions(permission = TaskPermission.READ)
    @Override
    public List<TimeSlice> getAllByTaskId(Long taskId, TimePoint startDate, TimePoint endDate) {
        return (List<TimeSlice>) timeSliceRepository.findAllByTaskId(taskId);
    }

    @Override
    public List<TimeSliceDto> getAllDtoByTaskIdAndUserId(Long taskId, Long userId, TimePoint startDate, TimePoint endDate) {
        List<TimeSlice> timeSlices = getAllByTaskIdAndUserId(taskId, userId, startDate, endDate);
        return timeSlices.stream().map(timeSliceMapper::toDto).toList();
    }

    @CheckTaskPermissions(permission = TaskPermission.READ)
    @Override
    public List<TimeSlice> getAllByTaskIdAndUserId(Long taskId, Long userId, TimePoint startDate, TimePoint endDate) {
        return null;
    }

    @Override
    public List<TimeSliceDto> getAllDtoByProjectIdAndUserId(Long projectId, Long userId, TimePoint startDate, TimePoint endDate) {
        List<TimeSlice> timeSlices = getAllByProjectIdAndUserId(projectId, userId, startDate, endDate);
        return timeSlices.stream().map(timeSliceMapper::toDto).toList();
    }

    @CheckProjectPermissions(permission = ProjectPermission.READ)
    @Override
    public List<TimeSlice> getAllByProjectIdAndUserId(Long projectId, Long userId, TimePoint startDate, TimePoint endDate) {
        return null;
    }

    @CheckTaskPermissions(permission = TaskPermission.CREATE)
    @Override
    public TimeSliceDto start(Long taskId, User user, String name) {
        Task task = taskService.getById(taskId);
        TimeSlice lastTimeSlice = getLastTimeSliceByTaskId(taskId);

        if (lastTimeSlice != null && lastTimeSlice.getEndTimePoint() == null) {
            throw new TimeSliceAlreadyStartException("Time slice in this task already start!");
        }

        TimePoint timePoint = new TimePoint(new Date());
        TimeSlice timeSlice = new TimeSlice(name,
                timePoint,
                task,
                user
        );
        return timeSliceMapper.toDto(timeSliceRepository.save(timeSlice));
    }

    @CheckTaskPermissions(permission = TaskPermission.CREATE)
    @Override
    public TimeSliceDto stop(Long taskId, User user) {
        TimeSlice lastTimeSlice = getLastTimeSliceByTaskId(taskId);

        if (lastTimeSlice != null && lastTimeSlice.getEndTimePoint() != null) {
            throw new TimeSliceAlreadyStopException("Time slice in this task already stop!");
        }

        TimePoint timePoint = new TimePoint(new Date());
        lastTimeSlice.setEndTimePoint(timePoint);

        return timeSliceMapper.toDto(timeSliceRepository.save(lastTimeSlice));
    }

    @CheckTaskPermissions(permission = TaskPermission.READ)
    @Override
    public TimeSlice getLastTimeSliceByTaskId(Long taskId) {
        return null;
    }
}
