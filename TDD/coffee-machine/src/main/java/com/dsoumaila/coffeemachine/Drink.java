package com.dsoumaila.coffeemachine;

public enum Drink {
    TEA("T"),
    COFFEE("C"),
    CHOCOLATE("H"),
    ORANGE("O");

    private String value;

    Drink(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
