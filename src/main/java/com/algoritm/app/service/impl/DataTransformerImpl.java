package com.algoritm.app.service.impl;

import com.algoritm.app.service.DataTransformer;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataTransformerImpl implements DataTransformer {

    @Override
    public int[] transform(String data) {
        String[] array = data.split("[\\s\\.,;]");

        return Arrays.stream(array).mapToInt(Integer::parseInt)
                .toArray();
    }
}
