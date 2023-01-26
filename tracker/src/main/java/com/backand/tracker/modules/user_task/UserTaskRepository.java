package com.backand.tracker.modules.user_task;

import com.backand.tracker.modules.user_task.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
}
