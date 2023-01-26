package com.backand.tracker.modules.time_slice;

import com.backand.tracker.modules.project_role_permission.ProjectRolePermissions;
import com.backand.tracker.modules.project_role_permission.dto.res.ProjectRolePermissionsDto;
import com.backand.tracker.modules.task.TaskRepository;
import com.backand.tracker.modules.time_slice.dto.res.TimeSliceDto;
import com.backand.tracker.modules.user.UserRepository;
import com.backand.tracker.utils.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class TimeSliceMapper extends AbstractMapper<TimeSlice, TimeSliceDto> {

    private final ModelMapper mapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TimeSliceMapper(
            ModelMapper mapper,
            TaskRepository taskRepository,
            UserRepository userRepository
    ) {
        super(TimeSlice.class, TimeSliceDto.class);
        this.mapper = mapper;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void setMapper() {
        mapper.createTypeMap(TimeSlice.class, TimeSliceDto.class)
                .addMappings(m -> m.skip(TimeSliceDto::setUserId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(TimeSliceDto::setTaskId)).setPostConverter(toDtoConverter());

        mapper.createTypeMap(TimeSliceDto.class, TimeSlice.class)
                .addMappings(m -> m.skip(TimeSlice::setUser)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(TimeSlice::setTask)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(TimeSlice source, TimeSliceDto destination) {
        destination.setUserId(getUserId(source));
        destination.setTaskId(getTaskId(source));
    }

    private Long getUserId(TimeSlice source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getUser().getId();
    }

    private Long getTaskId(TimeSlice source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getTask().getId();
    }

    @Override
    public void mapSpecificFields(TimeSliceDto source, TimeSlice destination) {
        destination.setUser(userRepository.findById(source.getUserId()).orElse(null));
        destination.setTask(taskRepository.findById(source.getTaskId()).orElse(null));
    }
}
