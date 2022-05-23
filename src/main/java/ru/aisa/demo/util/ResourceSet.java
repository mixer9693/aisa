package ru.aisa.demo.util;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResourceSet {
    private Integer water;
    private Integer coffee;
    private Integer milk;
    private Integer cups;

    public static class ResourceSetBuilder {
        public ResourceSetBuilder() {
            //empty
        }
    }
}
