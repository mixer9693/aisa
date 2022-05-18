package ru.aisa.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDto {
    private String coffee;
    private String size;
    private LocalDateTime created;
}
