package ru.aisa.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderOutputDto {
    private String uid;
    private String name;
    private String size;
    private String status;
    private String comment;
    private LocalDateTime updated;
}
