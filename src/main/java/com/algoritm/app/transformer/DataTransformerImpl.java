package com.algoritm.app.transformer;

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
