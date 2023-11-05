package com.daniil.project.utility;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.IntStream;

public class Utility{
    public static <T> List<Integer> getPageNumbers(Page<T> page){
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            return IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .toList();
        }
        return List.of(0);
    }

}
