package ru.aisa.demo.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum Size {
    SMALL(200), MEDIUM(300), LARGE(500);

    private final int value;
}
