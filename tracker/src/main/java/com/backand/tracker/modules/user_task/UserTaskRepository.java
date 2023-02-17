package com.backand.tracker.modules.user_task;

import com.backand.tracker.modules.user_task.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
    Optional<UserTask> findByTaskIdAndUserId(Long taskId, Long userId);
}
