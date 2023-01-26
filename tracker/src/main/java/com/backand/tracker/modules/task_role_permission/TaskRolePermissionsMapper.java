package com.backand.tracker.modules.task_role_permission;

import com.backand.tracker.modules.task_role.TaskRoleRepository;
import com.backand.tracker.modules.task_role_permission.dto.res.TaskRolePermissionsDto;
import com.backand.tracker.utils.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class TaskRolePermissionsMapper extends AbstractMapper<TaskRolePermissions, TaskRolePermissionsDto> {

    private final ModelMapper mapper;
    private final TaskRoleRepository taskRoleRepository;

    @Autowired
    public TaskRolePermissionsMapper(
            ModelMapper mapper,
            TaskRoleRepository taskRoleRepository
    ) {
        super(TaskRolePermissions.class, TaskRolePermissionsDto.class);
        this.mapper = mapper;
        this.taskRoleRepository = taskRoleRepository;
    }

    @PostConstruct
    public void setMapper() {
        mapper.createTypeMap(TaskRolePermissions.class, TaskRolePermissionsDto.class)
                .addMappings(m -> m.skip(TaskRolePermissionsDto::setTaskRoleId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(TaskRolePermissionsDto.class, TaskRolePermissions.class)
                .addMappings(m -> m.skip(TaskRolePermissions::setTaskRole)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(TaskRolePermissions source, TaskRolePermissionsDto destination) {
        destination.setTaskRoleId(getTaskRoleId(source));
    }

    private Long getTaskRoleId(TaskRolePermissions source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getTaskRole().getId();
    }

    @Override
    public void mapSpecificFields(TaskRolePermissionsDto source, TaskRolePermissions destination) {
        destination.setTaskRole(taskRoleRepository.findById(source.getTaskRoleId()).orElse(null));
    }
}
