package com.algoritm.app.util;


import com.algoritm.app.entity.algoritm.AlgoritmType;
import com.algoritm.app.exception.IncorrectAlgoritmTypeException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlgoritmUtil {

    public AlgoritmType getAlgoritmType(String type) throws IncorrectAlgoritmTypeException {
        AlgoritmType[] algoritmTypes = AlgoritmType.values();

        return Arrays.stream(algoritmTypes)
                .filter(a -> a.toString().equalsIgnoreCase(type))
                .findAny()
                .orElseThrow(() -> new IncorrectAlgoritmTypeException("Incorrect algoritm type"));
    }

    public List<AlgoritmType> getSortAlgoritms() {
        return Arrays.stream(AlgoritmType.values())
                .filter(type -> type.toString().contains("SORT"))
                .collect(Collectors.toList());
    }

    public List<AlgoritmType> getSearchAlgoritms() {
        return Arrays.stream(AlgoritmType.values())
                .filter(type -> type.toString().contains("SEARCH"))
                .collect(Collectors.toList());
    }
}
