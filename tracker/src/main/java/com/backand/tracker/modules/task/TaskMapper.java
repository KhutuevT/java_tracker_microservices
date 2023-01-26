package com.backand.tracker.modules.task;

import com.backand.tracker.modules.project.ProjectRepository;
import com.backand.tracker.modules.task.dto.res.TaskDto;
import com.backand.tracker.modules.user.UserRepository;
import com.backand.tracker.utils.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class TaskMapper extends AbstractMapper<Task, TaskDto> {

    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public TaskMapper(
            ModelMapper mapper,
            UserRepository userRepository,
            ProjectRepository projectRepository
    ) {
        super(Task.class, TaskDto.class);
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Task.class, TaskDto.class)
                .addMappings(m -> m.skip(TaskDto::setProjectId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(TaskDto::setCreatorId)).setPostConverter(toDtoConverter());

        mapper.createTypeMap(TaskDto.class, Task.class)
                .addMappings(m -> m.skip(Task::setProject)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Task::setCreator)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Task source, TaskDto destination) {
        destination.setProjectId(getProjectId(source));
        destination.setCreatorId(getCreatorId(source));
    }

    private Long getProjectId(Task source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getProject().getId();
    }

    private Long getCreatorId(Task source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getCreator().getId();
    }

    @Override
    public void mapSpecificFields(TaskDto source, Task destination) {
        destination.setCreator(userRepository.findById(source.getCreatorId()).orElse(null));
        destination.setProject(projectRepository.findById(source.getProjectId()).orElse(null));
    }
}
