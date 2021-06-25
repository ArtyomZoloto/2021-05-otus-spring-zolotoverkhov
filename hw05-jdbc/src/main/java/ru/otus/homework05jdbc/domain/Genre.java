package ru.otus.homework05jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Genre {
    private Long id;
    private String name;
}
