package ru.otus.domain;

/**
 * Simple user representation.
 */

public class User {
    private final String name, surname;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
