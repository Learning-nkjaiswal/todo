package com.microsoft.idc.bootcamp.todo.modals;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private UUID id;
    private String name;
    private Date eta;
    private boolean completed;
}
