package ru.petrov.findmax.util;

import ru.petrov.findmax.model.FileNotValid;

import java.io.File;

public class FileValidator {
    public static void validateFile(File file){

        if (!file.exists()) {
            throw new FileNotValid("Такой файл не существует.");
        }
        if (!file.isFile()) {
            throw new FileNotValid("Не является файлом");
        }
        if (!getFileExtension(file.getName()).equals("xlsx")) {
            throw new FileNotValid("Расширение должно быть xlsx.");
        }

    }

    private static String getFileExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex >= 0) {
            return filename.substring(dotIndex + 1);
        }
        return "";
    }

}
