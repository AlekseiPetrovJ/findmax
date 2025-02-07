package ru.petrov.findmax.util;

import ru.petrov.findmax.model.FileNotValid;

import java.io.File;

public class FileValidator {

    /**
     * Метод для проверки файла
     *
     * @param file
     */
    public static void validateFile(File file) {

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


    public static void validatePositionFile(int size, int n) {
        if (size-1 < n){
            throw new FileNotValid("В файле меньше строк чем запрошенная позиция");
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
