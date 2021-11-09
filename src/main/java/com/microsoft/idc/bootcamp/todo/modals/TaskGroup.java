package com.microsoft.idc.bootcamp.todo.modals;

import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskGroup {
    private UUID id;
    private String name;
    private Map<UUID, Task> tasks;

    public Task addTask(String name) {
        UUID taskId = UUID.randomUUID();
        Task newTask = Task.builder()
                .name(name)
                .id(taskId)
                .completed(false)
                .build();
        tasks.put(taskId, newTask);
        return newTask;
    }

    public Task markCompleted(UUID id) {
        validateTask(id);
        tasks.get(id).setCompleted(true);
        return tasks.get(id);
    }

    private void validateTask(UUID id) {
        if(!tasks.containsKey(id)) {
            throw new RuntimeException("Task Not Found");
        }
    }

    public Task removeTask(UUID id) {
        validateTask(id);
        Task task = tasks.get(id);
        tasks.remove(id);
        return task;
    }

    public Task updateTask(UUID id, String name, Date eta, boolean completed) {
        validateTask(id);
        Task task = tasks.get(id);
        task.setName(name);
        task.setEta(eta);
        task.setCompleted(completed);
        return task;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }
}
