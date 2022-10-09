package de.imc.test.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {
    private int id;
    private String title;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;
}
