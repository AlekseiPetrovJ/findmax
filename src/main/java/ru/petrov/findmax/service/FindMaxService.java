package ru.petrov.findmax.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.petrov.findmax.util.FileValidator;
import ru.petrov.findmax.util.MergeSort;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindMaxService {

    public Long findNMax(String path, Integer n) {
        File file = new File(path);
        FileValidator.validateFile(file);
        List<Long> allNumberFromFile = getAllNumberFromFile(file);

        FileValidator.validatePositionFile(allNumberFromFile.size(), n);
        // Замер времени для оценки производительности
//        long startTime1 = System.currentTimeMillis();
        List<Long> allNumberFromFileSorted = MergeSort.mergeSortDesc(allNumberFromFile);
//        long endTime1 = System.currentTimeMillis();
//        long timeElapsed1 = endTime1 - startTime1;
//        System.out.println("время на сортировку: " + timeElapsed1);
        return allNumberFromFileSorted.get(n);
    }

    private List<Long> getAllNumberFromFile(File file) {
        int columnIndex = 0;
        List<Long> columnData = new ArrayList<>();
        IOUtils.setByteArrayMaxOverride(1000000000);
//        long startTime = System.currentTimeMillis();
        try (InputStream is = Files.newInputStream(file.toPath())) {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell = row.getCell(columnIndex);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    columnData.add((long) cell.getNumericCellValue());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//        long endTime = System.currentTimeMillis();
//        long timeElapsed = endTime - startTime;
//        System.out.println("column size: " + columnData.size());
//        System.out.println("время на считывание файла: " + timeElapsed);

        return columnData;
    }
}
