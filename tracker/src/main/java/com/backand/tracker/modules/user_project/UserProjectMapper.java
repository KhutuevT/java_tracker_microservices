package com.backand.tracker.modules.user_project;

import com.backand.tracker.modules.project.ProjectRepository;
import com.backand.tracker.modules.project_role.ProjectRoleRepository;
import com.backand.tracker.modules.time_slice.TimeSlice;
import com.backand.tracker.modules.time_slice.dto.res.TimeSliceDto;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user.UserRepository;
import com.backand.tracker.modules.user_project.dto.res.UserProjectDto;
import com.backand.tracker.utils.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class UserProjectMapper extends AbstractMapper<UserProject, UserProjectDto> {

    private final ModelMapper mapper;

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ProjectRoleRepository projectRoleRepository;

    @Autowired
    public UserProjectMapper(
            ModelMapper mapper,
            UserRepository userRepository,
            ProjectRepository projectRepository,
            ProjectRoleRepository projectRoleRepository
    ) {
        super(UserProject.class, UserProjectDto.class);
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.projectRoleRepository = projectRoleRepository;
    }

    @PostConstruct
    public void setMapper() {
        mapper.createTypeMap(UserProject.class, UserProjectDto.class)
                .addMappings(m -> m.skip(UserProjectDto::setUserId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(UserProjectDto::setProjectId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(UserProjectDto::setProjectRoleId)).setPostConverter(toDtoConverter());

        mapper.createTypeMap(UserProjectDto.class, UserProject.class)
                .addMappings(m -> m.skip(UserProject::setUser)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(UserProject::setProject)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(UserProject::setProjectRole)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(UserProject source, UserProjectDto destination) {
        destination.setUserId(getUserId(source));
        destination.setProjectId(getProjectId(source));
        destination.setProjectRoleId(getProjectRoleId(source));
    }

    private Long getUserId(UserProject source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getUser().getId();
    }

    private Long getProjectId(UserProject source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getProject().getId();
    }

    private Long getProjectRoleId(UserProject source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getProjectRole().getId();
    }

    @Override
    public void mapSpecificFields(UserProjectDto source, UserProject destination) {
        destination
                .setUser(userRepository.findById(source.getUserId()).orElse(null));
        destination
                .setProject(projectRepository.findById(source.getProjectId()).orElse(null));
        destination
                .setProjectRole(projectRoleRepository.findById(source.getProjectRoleId()).orElse(null));
    }
}
