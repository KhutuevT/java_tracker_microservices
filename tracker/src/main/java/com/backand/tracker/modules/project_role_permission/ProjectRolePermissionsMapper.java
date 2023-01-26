package com.backand.tracker.modules.project_role_permission;

import com.backand.tracker.modules.project.ProjectRepository;
import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.modules.project_role.ProjectRoleRepository;
import com.backand.tracker.modules.project_role.dto.res.ProjectRoleDto;
import com.backand.tracker.modules.project_role_permission.dto.res.ProjectRolePermissionsDto;
import com.backand.tracker.utils.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class ProjectRolePermissionsMapper extends AbstractMapper<ProjectRolePermissions, ProjectRolePermissionsDto> {

    private final ModelMapper mapper;
    private final ProjectRoleRepository projectRoleRepository;


    @Autowired
    public ProjectRolePermissionsMapper(
            ModelMapper mapper,
            ProjectRoleRepository projectRoleRepository
    ) {
        super(ProjectRolePermissions.class, ProjectRolePermissionsDto.class);
        this.mapper = mapper;
        this.projectRoleRepository = projectRoleRepository;
    }

    @PostConstruct
    public void setMapper() {
        mapper.createTypeMap(ProjectRolePermissions.class, ProjectRolePermissionsDto.class)
                .addMappings(m -> m.skip(ProjectRolePermissionsDto::setProjectRoleId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(ProjectRolePermissionsDto.class, ProjectRolePermissions.class)
                .addMappings(m -> m.skip(ProjectRolePermissions::setProjectRole)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(ProjectRolePermissions source, ProjectRolePermissionsDto destination) {
        destination.setProjectRoleId(getProjectRoleId(source));
    }

    private Long getProjectRoleId(ProjectRolePermissions source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getProjectRole().getId();
    }

    @Override
    public void mapSpecificFields(ProjectRolePermissionsDto source, ProjectRolePermissions destination) {
        destination.setProjectRole(projectRoleRepository.findById(source.getProjectRoleId()).orElse(null));
    }
}

