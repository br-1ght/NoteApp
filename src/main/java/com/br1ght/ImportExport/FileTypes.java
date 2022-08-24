package com.br1ght.ImportExport;

public enum FileTypes {
    JSON(".json"),
    CSV(".csv");

    private final String value;
    FileTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
