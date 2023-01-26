package com.backand.tracker.modules.project;

import com.backand.tracker.modules.project.dto.req.CreateProjectReqDto;
import com.backand.tracker.modules.project.dto.res.ProjectDto;
import com.backand.tracker.modules.project.service.ProjectService;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user.services.UserService;
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

    UserService userService;
    ProjectService projectService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    ProjectRestControllerV1(
            ProjectService projectService,
            UserService userService
    ) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDto> getById(
            @PathVariable Long projectId,
            Principal principal
    ) {
        User user = userService.getUserByUsername(principal.getName());

        ProjectDto project = projectService
                .getById(user, projectId);

        return new ResponseEntity<ProjectDto>(project, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createNewProject(
            @RequestPart CreateProjectReqDto reqDto,
            @RequestPart MultipartFile avatarImage,
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

        ProjectDto savedProject = projectService
                .createNewProject(project);

        return new ResponseEntity<ProjectDto>(savedProject, HttpStatus.OK);
    }

//    @DeleteMapping()
//    public ResponseEntity deleteProject(
//            @RequestBody Long projectId,
//            Principal principal
//    ) {
//        User user = userService.getUserByUsername(principal.getName());
//        projectService.deleteProject(user, projectId);
//        return new ResponseEntity(HttpStatus.OK);
//    }

//    @PostMapping("/{projectId}/employee")
//    public ResponseEntity addEmployeeInProject(
//            @PathVariable Long projectId,
//            @RequestBody Long employeeUserId,
//            Principal principal
//    ) {
//        User user = userService.getUserByUsername(principal.getName());
//        projectService.addEmployeeInProject(user, projectId, employeeUserId);
//        return null;
//    }
//
//    @DeleteMapping("/{projectId}/employee")
//    public ResponseEntity deleteEmployeeInProject(
//            @PathVariable Long projectId,
//            @RequestBody Long employeeUserId,
//            Principal principal
//    ) {
//        User user = userService.getUserByUsername(principal.getName());
//        projectService.deleteEmployeeInProject(user, projectId, employeeUserId);
//        return null;
//    }
}
