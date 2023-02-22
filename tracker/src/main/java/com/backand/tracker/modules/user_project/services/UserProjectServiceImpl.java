package com.backand.tracker.modules.user_project.services;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.project_role.ProjectRole;
import com.backand.tracker.modules.user_project.UserProject;
import com.backand.tracker.modules.user_project.services.UserProjectService;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.user_project.UserProjectRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public UserProject getByProjectIdAndUserId(Long projectId, Long userId) {
        return userProjectRepository.findUserProjectByProjectIdAndUserId(projectId, userId)
                .orElseThrow(() -> new EntityNotFoundException("User project not found!"));
    }

    @Override
    public UserProject createNewUserProject(User user, Project project, ProjectRole projectRole) {
        return null;
    }

    @Override
    public UserProject deleteUserProject(User user, Project project) {
        return null;
    }

    @Override
    public void addEmployeeInProject(User user, Long projectId, Long employeeUserId) {

    }

    @Override
    public void deleteEmployeeInProject(User user, Long projectId, Long employeeUserId) {

    }


//    @Override
//    public void addEmployeeInProject(
//            User user,
//            Long projectId,
//            Long employeeUserId
//    ) {
//        User employee = userService.getUser(employeeUserId);
//
//        Project project = getById(projectId);
//
//        ProjectRole onlyReadRole = projectRoleService
//                .getByName(user, String.valueOf(ProjectPermissionsEnum.READ));
//
//        userProjectService
//                .createNewUserProject(employee, project, onlyReadRole);
//    }
//
//    @Override
//    public void deleteEmployeeInProject(
//            User user,
//            Long projectId,
//            Long employeeUserId
//    ) {
//        User employee = userService.getUser(employeeUserId);
//
//        Project project = getById(projectId);
//
//        userProjectService
//                .deleteUserProject(employee, project);
//    }
}
