package com.algoritm.app.entity.sortImpl;


import com.algoritm.app.entity.algoritm.AbstractSortAlgoritm;
import com.algoritm.app.entity.algoritm.AlgoritmResult;

public class BubbleSort extends AbstractSortAlgoritm {

    @Override
    public AlgoritmResult getAlgoritmResult(int[] data) {
        boolean isSorted = false;

        int buf = 0;

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < data.length - 1; i++) {
                if (data[i] > data[i + 1]) {
                    isSorted = false;

                    buf = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = buf;
                }
            }
        }

        return AlgoritmResult.builder()
                .sortResult(data)
                .build();
    }
}
