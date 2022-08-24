package com.br1ght.App;

import java.util.Arrays;

public enum MenuItems {
    EXIT("0"),
    VIEW("1"),
    CREATE("2"),
    DELETE("3"),
    UPDATE("4"),
    EXPORT("5");

    private final String value;

    MenuItems(String value) {
        this.value = value;
    }

    public static MenuItems getEnumFromString(String choice) {
        return Arrays.stream(values())
                .filter(menuItems -> menuItems.value.equals(choice))
                .findFirst()
                .orElse(null);
    }
}
