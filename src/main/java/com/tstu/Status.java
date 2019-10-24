package com.tstu;

public enum Status {
    GIVE_UP("Игрок сдался"),
    MISTAKE("Введены неверные данные"),
    WIN("Вы отгадали последовательность!"),
    CONTINUE("Продолжить");


    private String value;

    public String getValue() {
        return value;
    }

    Status(String value) {
        this.value = value;
    }
}
