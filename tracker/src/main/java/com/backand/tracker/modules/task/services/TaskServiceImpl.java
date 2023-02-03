package com.backand.tracker.modules.task.services;

import com.backand.tracker.modules.project.Project;
import com.backand.tracker.modules.task.Task;
import com.backand.tracker.modules.user.User;
import com.backand.tracker.modules.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(
            TaskRepository taskRepository
    ) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTaskByProjectId(Long projectId) {
        List<Task> tasks = (List<Task>) taskRepository.getTaskByProjectId(projectId);
        return tasks;
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found!"));
    }

    @Override
    public Task createNewTask(
            User user,
            String name,
            String description,
            Project project
    ) {
        Task task = new Task(name,
                description,
                project,
                user
        );

        Task saveTask = taskRepository.save(task);
        return saveTask;
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
