package com.backand.tracker.modules.user_project;

import com.backand.tracker.modules.user_project.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProjectRepository extends JpaRepository<UserProject, Long> {
    Optional<UserProject> findUserProjectByProjectIdAndUserId(Long projectId, Long userId);
}
