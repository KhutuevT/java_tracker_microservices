package com.backand.tracker.modules.project;

import com.backand.tracker.modules.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProjectRepository  extends JpaRepository<Project, Long> {
//    @Query("from Project p " +
//            "left join fetch p.tasks " +
//            "left join fetch p.userProjects " +
//            "left join fetch p.projectRoles " +
//            "where p.id = :id")
    Optional<Project> getProjectById(Long id);
}
