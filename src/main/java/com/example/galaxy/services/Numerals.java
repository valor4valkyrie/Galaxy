package com.example.galaxy.services;

public enum Numerals {

    I( "I",1), V("V", 5), X("X", 10), L("L", 50), C("C", 100),
    D("D", 500), M("M", 1000);

    private final int value;
    private final String id;

    Numerals(String id, int value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public int getValue() {
        return value;
    }
}
