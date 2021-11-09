package com.microsoft.idc.bootcamp.todo.modals;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateRequest {
    private String name;
    private Date eta;
    private boolean completed;
}
