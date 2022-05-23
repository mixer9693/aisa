package ru.aisa.demo.service;

import ru.aisa.demo.dto.RefillInputDto;
import ru.aisa.demo.dto.RefillOutputDto;
import ru.aisa.demo.entity.Refill;
import ru.aisa.demo.enums.RefillStatus;

public interface RefillService {
    RefillOutputDto create(RefillInputDto dto);
    RefillOutputDto get(Integer id);
    void updateStatus(Refill refill, RefillStatus status);
}
