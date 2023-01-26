package com.backand.tracker.modules.task_role;

import com.backand.tracker.modules.task.TaskRepository;
import com.backand.tracker.modules.task_role.dto.res.TaskRoleDto;
import com.backand.tracker.modules.user.UserRepository;
import com.backand.tracker.utils.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class TaskRoleMapper extends AbstractMapper<TaskRole, TaskRoleDto> {

    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskRoleMapper(
            ModelMapper mapper,
            UserRepository userRepository,
            TaskRepository taskRepository
    ) {
        super(TaskRole.class, TaskRoleDto.class);
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(TaskRole.class, TaskRoleDto.class)
                .addMappings(m -> m.skip(TaskRoleDto::setTaskId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(TaskRoleDto::setCreatorId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(TaskRoleDto.class, TaskRole.class)
                .addMappings(m -> m.skip(TaskRole::setTask)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(TaskRole::setCreator)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(TaskRole source, TaskRoleDto destination) {
        destination.setTaskId(getTaskId(source));
        destination.setCreatorId(getCreatorId(source));
    }

    private Long getTaskId(TaskRole source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getTask().getId();
    }

    private Long getCreatorId(TaskRole source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getCreator().getId();
    }

    @Override
    public void mapSpecificFields(TaskRoleDto source, TaskRole destination) {
        destination.setCreator(userRepository.findById(source.getCreatorId()).orElse(null));
        destination.setTask(taskRepository.findById(source.getTaskId()).orElse(null));
    }
}
