package com.microsoft.idc.bootcamp.todo.controllers;

import com.microsoft.idc.bootcamp.todo.dao.TaskDB;
import com.microsoft.idc.bootcamp.todo.modals.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TaskGroupController {

    private final TaskDB taskDB = TaskDB.getInstance();

    @GetMapping("/api/v1/TaskGroup")
    public List<TaskGroupResponse> getTaskGroups(@RequestHeader(value = "user") String user) {
        return taskDB.getTaskGroup(user);
    }

    @PostMapping("/api/v1/TaskGroup")
    public TaskGroup createTaskGroup(@RequestBody TaskGroupRequest taskGroupRequest,
                                     @RequestHeader(value = "user") String user) {
        return taskDB.addTaskGroup(user, taskGroupRequest.getName());
    }

    @GetMapping("/api/v1/TaskGroup/{taskGroupId}/tasks")
    public List<Task> getTasks(@RequestHeader(value = "user") String user, @PathVariable("taskGroupId") UUID taskGroupId) {
        return taskDB.getTasks(user, taskGroupId);
    }

    @PutMapping("/api/v1/TaskGroup/{taskGroupId}/task/{taskId}")
    public Task updateTask(@RequestHeader(value = "user") String user,
                                 @PathVariable("taskGroupId") UUID taskGroupId,
                                 @PathVariable("taskId") UUID taskId,
                                 @RequestBody TaskUpdateRequest taskUpdateRequest) {
        return taskDB.updateTask(user, taskGroupId, taskId, taskUpdateRequest.getName(), taskUpdateRequest.getEta(), taskUpdateRequest.isCompleted());
    }

    @DeleteMapping("/api/v1/TaskGroup/{taskGroupId}/task/{taskId}")
    public Task removeTask(@RequestHeader(value = "user") String user,
                           @PathVariable("taskGroupId") UUID taskGroupId,
                           @PathVariable("taskId") UUID taskId) {
        return taskDB.removeTask(user, taskGroupId, taskId);
    }

    @PutMapping("/api/v1/TaskGroup/{taskGroupId}/task/{taskId}/complete")
    public Task markCompleted(@RequestHeader(value = "user") String user,
                           @PathVariable("taskGroupId") UUID taskGroupId,
                           @PathVariable("taskId") UUID taskId) {
        return taskDB.markCompleted(user, taskGroupId, taskId);
    }

    @PostMapping("/api/v1/TaskGroup/{taskGroupId}/task")
    public Task addTask(@RequestHeader(value = "user") String user,
                              @PathVariable("taskGroupId") UUID taskGroupId,
                              @RequestBody TaskGroupRequest task) {
        return taskDB.addTask(user, taskGroupId, task.getName());
    }
}
