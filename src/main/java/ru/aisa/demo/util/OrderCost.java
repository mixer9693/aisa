package ru.aisa.demo.util;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderCost {
    private Integer water;
    private Integer coffee;
    private Integer milk;
    private Integer cups;

    public static class OrderCostBuilder {
        public OrderCostBuilder() {
            //empty
        }
    }
}
