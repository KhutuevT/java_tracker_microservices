package com.backand.tracker.modules.project;

import com.backand.tracker.modules.project.dto.res.ProjectDto;
import com.backand.tracker.modules.user.UserRepository;
import com.backand.tracker.utils.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class ProjectMapper extends AbstractMapper<Project, ProjectDto> {

    private final ModelMapper mapper;
    private final UserRepository userRepository;


    @Autowired
    public ProjectMapper(
            ModelMapper mapper,
            UserRepository userRepository
    ) {
        super(Project.class, ProjectDto.class);
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Project.class, ProjectDto.class)
                .addMappings(m -> m.skip(ProjectDto::setCreatorId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(ProjectDto.class, Project.class)
                .addMappings(m -> m.skip(Project::setCreator)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Project source, ProjectDto destination) {
        destination.setCreatorId(getId(source));
    }

    private Long getId(Project source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getCreator().getId();
    }

    @Override
    public void mapSpecificFields(ProjectDto source, Project destination) {
        destination.setCreator(userRepository.findById(source.getCreatorId()).orElse(null));
    }
}
