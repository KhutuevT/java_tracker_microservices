package com.backand.tracker.modules.project;

import com.backand.tracker.modules.project.dto.req.CreateProjectReqDto;
import com.backand.tracker.modules.project.dto.req.UpdateProjectDto;
import com.backand.tracker.modules.project.dto.res.ProjectDto;
import com.backand.tracker.modules.project.service.ProjectService;
import com.backand.tracker.modules.project_role_permission.ProjectPermissionsEnum;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user.services.UserService;
import com.backand.tracker.utils.UserPermissionsCheck;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/projects")
public class ProjectRestControllerV1 {

    private final UserService userService;
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    ProjectRestControllerV1(
            ProjectService projectService,
            UserService userService,
            ProjectMapper projectMapper
    ) {
        this.projectService = projectService;
        this.userService = userService;
        this.projectMapper = projectMapper;
    }

    @Operation(summary = "Возвращает проект по projectId")
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDto> getById(
            @Parameter(description = "id проекта")
            @PathVariable
            Long projectId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getById(projectId);

        UserPermissionsCheck.checkUserPermissionInProjectWithException(user, project, ProjectPermissionsEnum.READ);

        ProjectDto projectDto = projectMapper.toDto(project);
        return new ResponseEntity<ProjectDto>(projectDto, HttpStatus.OK);
    }

    @Operation(summary = "Создаёт новый проект")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProjectDto> createNewProject(
            @RequestPart
            @Parameter(schema =@Schema(type = "string", format = "binary"))
            CreateProjectReqDto reqDto,
            @Parameter(description = "Изображение (аватарка) проекта")
            @RequestPart
            MultipartFile avatarImage,
            Principal principal
    ) throws IOException {
        User user = userService.getUserByUsername(principal.getName());
        Project project = null;

        project = new Project.Builder(reqDto.getName(), user)
                .descriptions(reqDto.getDescriptions())
                .build();

        if (avatarImage != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + avatarImage.getOriginalFilename();
            avatarImage.transferTo(new File(uploadPath + "/" + resultFileName));

            project = new Project.Builder(reqDto.getName(), user)
                    .descriptions(reqDto.getDescriptions())
                    .image(resultFileName)
                    .build();
        }

        Project savedProject = projectService
                .createNewProject(project);

        ProjectDto saveProjectDto = projectMapper.toDto(savedProject);
        return new ResponseEntity<>(saveProjectDto, HttpStatus.OK);
    }

    @Operation(summary = "Удаляет проект")
    @DeleteMapping("/{projectId}")
    public ResponseEntity deleteProject(
            @PathVariable Long projectId,
            Principal principal
            ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getById(projectId);

        UserPermissionsCheck.checkUserPermissionInProjectWithException(user, project, ProjectPermissionsEnum.DELETE);

        projectService.deleteProject(projectId);

        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @Operation(summary = "Изменить проект")
    @PatchMapping("/{projectId}")
    public ResponseEntity<ProjectDto> updateProject(
            @PathVariable Long projectId,
            @RequestBody UpdateProjectDto updateProjectDto,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());
        Project project = projectService.getById(projectId);

        UserPermissionsCheck.checkUserPermissionInProjectWithException(user, project, ProjectPermissionsEnum.UPDATE);

        //TODO

        return new ResponseEntity("OK", HttpStatus.OK);
    }
}
