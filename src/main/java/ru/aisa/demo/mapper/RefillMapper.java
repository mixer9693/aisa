package ru.aisa.demo.mapper;

import org.mapstruct.Mapper;
import ru.aisa.demo.dto.RefillInputDto;
import ru.aisa.demo.dto.RefillOutputDto;
import ru.aisa.demo.entity.CoffeeMachine;
import ru.aisa.demo.entity.Refill;
import ru.aisa.demo.util.ResourceSet;

@Mapper(componentModel = "spring")
public interface RefillMapper {
    Refill dtoToRefill(RefillInputDto dto);
    RefillOutputDto refillToDto(Refill refill);

    ResourceSet refillToResourceSet(Refill refill);

    default CoffeeMachine idToCoffeeMachine(Integer id){
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.setId(id);
        return coffeeMachine;
    }
}
