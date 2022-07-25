package com.algoritm.app.entity.sortImpl;


import com.algoritm.app.entity.algoritm.AbstractSortAlgoritm;
import com.algoritm.app.entity.algoritm.AlgoritmResult;

public class SelectSort extends AbstractSortAlgoritm {

    @Override
    public AlgoritmResult getAlgoritmResult(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int pos = i;
            int min = data[i];

            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < min) {
                    pos = j;
                    min = data[j];
                }
            }
            data[pos] = data[i];
            data[i] = min;
        }

        return AlgoritmResult.builder()
                .sortResult(data)
                .build();
    }
}