package com.backand.tracker.modules.user_project;

import com.backand.tracker.modules.user_project.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProjectRepository extends JpaRepository<UserProject, Long> {
}
