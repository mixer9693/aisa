package ru.aisa.demo.dto;

import lombok.Data;

@Data
public class RefillInputDto {
    private Integer water;
    private Integer coffee;
    private Integer milk;
    private Integer cups;
    private Integer machineId;
}
