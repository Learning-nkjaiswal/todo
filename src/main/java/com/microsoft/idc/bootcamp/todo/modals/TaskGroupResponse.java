package com.microsoft.idc.bootcamp.todo.modals;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskGroupResponse {

    private UUID id;
    private String name;

}
