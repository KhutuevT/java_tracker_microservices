package com.backand.tracker.modules.project_role;

import com.backand.tracker.modules.project.ProjectRepository;
import com.backand.tracker.modules.project_role.dto.res.ProjectRoleDto;
import com.backand.tracker.modules.user.UserRepository;
import com.backand.tracker.utils.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class ProjectRoleMapper extends AbstractMapper<ProjectRole, ProjectRoleDto> {

    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectRoleMapper(
            ModelMapper mapper,
            UserRepository userRepository,
            ProjectRepository projectRepository
    ) {
        super(ProjectRole.class, ProjectRoleDto.class);
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(ProjectRole.class, ProjectRoleDto.class)
                .addMappings(m -> m.skip(ProjectRoleDto::setProjectId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ProjectRoleDto::setCreatorId)).setPostConverter(toDtoConverter());

        mapper.createTypeMap(ProjectRoleDto.class, ProjectRole.class)
                .addMappings(m -> m.skip(ProjectRole::setProject)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(ProjectRole::setCreator)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(ProjectRole source, ProjectRoleDto destination) {
        destination.setProjectId(getProjectId(source));
        destination.setCreatorId(getCreatorId(source));
    }

    private Long getProjectId(ProjectRole source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getProject().getId();
    }

    private Long getCreatorId(ProjectRole source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getCreator().getId();
    }

    @Override
    public void mapSpecificFields(ProjectRoleDto source, ProjectRole destination) {
        destination.setCreator(userRepository.findById(source.getCreatorId()).orElse(null));
        destination.setProject(projectRepository.findById(source.getProjectId()).orElse(null));
    }
}
