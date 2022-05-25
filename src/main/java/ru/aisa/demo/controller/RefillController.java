package ru.aisa.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.aisa.demo.dto.RefillInputDto;
import ru.aisa.demo.dto.RefillOutputDto;
import ru.aisa.demo.service.RefillService;

@RestController
@RequiredArgsConstructor
public class RefillController {

    private final RefillService refillService;

    @PostMapping("/coffee_machine/{id}/refills")
    public RefillOutputDto create(@RequestBody RefillInputDto dto, @PathVariable("id") Integer machineId){
        dto.setMachineId(machineId);
        return refillService.create(dto);
    }

    @GetMapping("/refills/{id}")
    public RefillOutputDto get(@PathVariable Integer id){
        return refillService.get(id);
    }

}
