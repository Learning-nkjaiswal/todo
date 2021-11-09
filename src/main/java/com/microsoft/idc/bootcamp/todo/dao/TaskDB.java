package com.microsoft.idc.bootcamp.todo.dao;

import com.microsoft.idc.bootcamp.todo.modals.Task;
import com.microsoft.idc.bootcamp.todo.modals.TaskGroup;
import com.microsoft.idc.bootcamp.todo.modals.TaskGroupResponse;

import java.util.*;
import java.util.stream.Collectors;

public class TaskDB {

    private static final TaskDB instance = new TaskDB();

    private final Map<String, Map<UUID, TaskGroup>> inMemDb = new HashMap<>();

    private TaskDB() {

    }

    public List<TaskGroupResponse> getTaskGroup(String user) {
        createUserIfNotExists(user);
        return new ArrayList<>(inMemDb.get(user).values()).stream().map(t -> new TaskGroupResponse(t.getId(), t.getName())).collect(Collectors.toList());
    }

    public static TaskDB getInstance() {
        return instance;
    }

    public TaskGroup addTaskGroup(String user, String name) {
        UUID id = UUID.randomUUID();
        createUserIfNotExists(user);
        TaskGroup taskGroup = TaskGroup.builder()
                .id(id)
                .name(name)
                .tasks(new HashMap<>())
                .build();
        inMemDb.get(user).put(id, taskGroup);
        return taskGroup;
    }

    public List<Task> getTasks(String user, UUID taskGroupId) {
        validateTaskGroup(user, taskGroupId);
        return inMemDb.get(user).get(taskGroupId).getTasks();
    }

    public Task updateTask(String user, UUID taskGroupId, UUID taskId, String name, Date eta, boolean completed) {
        validateTaskGroup(user, taskGroupId);
        return inMemDb.get(user).get(taskGroupId).updateTask(taskId, name, eta, completed);
    }

    public Task removeTask(String user, UUID taskGroupId, UUID taskId) {
        validateTaskGroup(user, taskGroupId);
        return inMemDb.get(user).get(taskGroupId).removeTask(taskId);
    }

    public Task markCompleted(String user, UUID taskGroupId, UUID taskId) {
        validateTaskGroup(user, taskGroupId);
        return inMemDb.get(user).get(taskGroupId).markCompleted(taskId);
    }

    public Task addTask(String user, UUID taskGroupId, String name) {
        validateTaskGroup(user, taskGroupId);
        return inMemDb.get(user).get(taskGroupId).addTask(name);
    }

    private void validateUser(String user) {
        if (!inMemDb.containsKey(user)) {
            throw new RuntimeException("User Not Found");
        }
    }

    private void validateTaskGroup(String user, UUID taskGroup) {
        validateUser(user);
        if(!inMemDb.get(user).containsKey(taskGroup)) {
            throw new RuntimeException("Task Group Not Found");
        }
    }

    private void createUserIfNotExists(String user) {
        if(!inMemDb.containsKey(user)) {
            inMemDb.put(user, new HashMap<>());
        }
    }
}
