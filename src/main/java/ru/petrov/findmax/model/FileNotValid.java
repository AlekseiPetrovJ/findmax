package ru.petrov.findmax.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FileNotValid extends RuntimeException{

    public FileNotValid(String message) {
        super(message);
    }
}
