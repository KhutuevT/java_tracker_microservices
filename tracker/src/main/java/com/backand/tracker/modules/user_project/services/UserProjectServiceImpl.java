package com.backand.tracker.modules.user_project.services;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.modules.user_project.UserProject;
import com.backand.tracker.modules.user_project.services.UserProjectService;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user_project.UserProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProjectServiceImpl implements UserProjectService {

    private final UserProjectRepository userProjectRepository;

    @Autowired
    public UserProjectServiceImpl(UserProjectRepository userProjectRepository) {
        this.userProjectRepository = userProjectRepository;
    }

    @Override
    public UserProject createNewUserProject(User user, Project project, ProjectRole projectRole) {
        return null;
    }

    @Override
    public UserProject deleteUserProject(User user, Project project) {
        return null;
    }
}
