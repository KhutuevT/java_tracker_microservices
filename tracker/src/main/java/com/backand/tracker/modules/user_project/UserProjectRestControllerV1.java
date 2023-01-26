package com.backand.tracker.modules.user_project;

import com.backand.tracker.modules.user.UserRepository;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user_project.services.UserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/projects/{projectId}/user-projects")
public class UserProjectRestControllerV1 {

    private UserRepository userRepository;
    private UserProjectService userProjectService;

    @Autowired
    public UserProjectRestControllerV1(
            UserRepository userRepository,
            UserProjectService userProjectService
    ) {
        this.userRepository = userRepository;
        this.userProjectService = userProjectService;
    }

    @GetMapping()
    public ResponseEntity get(
            @PathVariable String projectId,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return null;
    }

    @GetMapping("/{userProjectId}")
    public ResponseEntity getById(
            @PathVariable String projectId,
            @PathVariable String userProjectId,
            Principal principal
    ) {
        User user = userRepository.findByUsername(principal.getName()).get();
        return null;
    }
}
