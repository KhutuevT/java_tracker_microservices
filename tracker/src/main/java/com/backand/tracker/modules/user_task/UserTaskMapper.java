package com.backand.tracker.modules.user_task;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project.ProjectRepository;
import com.backand.tracker.modules.project.dto.res.ProjectDto;
import com.backand.tracker.modules.project_role.ProjectRoleRepository;
import com.backand.tracker.modules.task.TaskRepository;
import com.backand.tracker.modules.task_role.TaskRoleRepository;
import com.backand.tracker.modules.user.UserRepository;
import com.backand.tracker.modules.user_project.UserProject;
import com.backand.tracker.modules.user_project.dto.res.UserProjectDto;
import com.backand.tracker.modules.user_task.dto.res.UserTaskDto;
import com.backand.tracker.utils.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class UserTaskMapper extends AbstractMapper<UserTask, UserTaskDto> {

    private final ModelMapper mapper;

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskRoleRepository taskRoleRepository;

    @Autowired
    public UserTaskMapper(
            ModelMapper mapper,
            UserRepository userRepository,
            TaskRepository taskRepository,
            TaskRoleRepository taskRoleRepository
    ) {
        super(UserTask.class, UserTaskDto.class);
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.taskRoleRepository = taskRoleRepository;
    }

    @PostConstruct
    public void setMapper() {
        mapper.createTypeMap(UserTask.class, UserTaskDto.class)
                .addMappings(m -> m.skip(UserTaskDto::setUserId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(UserTaskDto::setTaskId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(UserTaskDto::setTaskRoleId)).setPostConverter(toDtoConverter());

        mapper.createTypeMap(UserTaskDto.class, UserTask.class)
                .addMappings(m -> m.skip(UserTask::setUser)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(UserTask::setTask)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(UserTask::setTaskRole)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(UserTask source, UserTaskDto destination) {
        destination.setUserId(getUserId(source));
        destination.setTaskId(getTaskId(source));
        destination.setTaskRoleId(getTaskRoleId(source));
    }

    private Long getUserId(UserTask source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getUser().getId();
    }

    private Long getTaskId(UserTask source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getTask().getId();
    }

    private Long getTaskRoleId(UserTask source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getTaskRole().getId();
    }

    @Override
    public void mapSpecificFields(UserTaskDto source, UserTask destination) {
        destination
                .setUser(userRepository.findById(source.getUserId()).orElse(null));
        destination
                .setTask(taskRepository.findById(source.getTaskId()).orElse(null));
        destination
                .setTaskRole(taskRoleRepository.findById(source.getTaskRoleId()).orElse(null));
    }
}
