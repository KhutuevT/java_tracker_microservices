package com.backand.tracker.modules.project;

import com.backand.tracker.modules.project.dto.req.CreateProjectReqDto;
import com.backand.tracker.modules.project.dto.req.UpdateProjectReqDto;
import com.backand.tracker.modules.project.dto.res.ProjectDto;
import com.backand.tracker.modules.project.service.ProjectService;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user.services.UserService;
import com.backand.tracker.annotations.CurrentUser;
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
            @CurrentUser
            User user
    ) {
        Project project = projectService.getById(projectId);
        System.out.println(user);
        ProjectDto projectDto = projectService.getDtoById(projectId, user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectDto);
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
            @CurrentUser User user
    ) throws IOException {
        ProjectDto saveProjectDto = projectService.createNew(reqDto, user);
        String avatarFileName = "default.png";
        if (avatarImage != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            avatarFileName = uuidFile + "." + avatarImage.getOriginalFilename();
            avatarImage.transferTo(new File(uploadPath + "/" + avatarFileName));

        }
        ProjectDto resultProjectDto = projectService.updateAvatar(saveProjectDto.getId(), user, avatarFileName);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resultProjectDto);
    }

    @Operation(summary = "Удаляет проект")
    @DeleteMapping("/{projectId}")
    public ResponseEntity deleteProject(
            @PathVariable Long projectId,
            @CurrentUser User user
            ) {
        Project project = projectService.getById(projectId);
        projectService.delete(projectId);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @Operation(summary = "Изменить проект")
    @PatchMapping("/{projectId}")
    public ResponseEntity<ProjectDto> updateProject(
            @PathVariable Long projectId,
            @RequestBody UpdateProjectReqDto updateProjectDto,
            @CurrentUser User user
    ) {
        Project project = projectService.getById(projectId);
        ProjectDto projectDto = projectService.update(projectId, updateProjectDto, user);

        return new ResponseEntity(projectDto, HttpStatus.OK);
    }
}
