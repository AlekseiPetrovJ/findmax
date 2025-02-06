package ru.petrov.findmax.util;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static List<Long> mergeSort(List<Long> list) {
        // Базовый случай: если список содержит 0 или 1 элемент, он уже отсортирован
        if (list.size() <= 1) {
            return list;
        }

        // Разделяем список на две части
        int middle = list.size() / 2;
        List<Long> left = mergeSort(list.subList(0, middle));
        List<Long> right = mergeSort(list.subList(middle, list.size()));

        // Объединяем отсортированные части
        return merge(left, right);
    }

    private static List<Long> merge(List<Long> left, List<Long> right) {
        List<Long> merged = new ArrayList<>();
        int i = 0, j = 0;

        // Сравниваем элементы двух списков и добавляем меньший в merged
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;
            }
        }

        // Добавляем оставшиеся элементы из левого списка
        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }

        // Добавляем оставшиеся элементы из правого списка
        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }
        return merged;
    }
}
