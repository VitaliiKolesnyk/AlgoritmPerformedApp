package com.algoritm.app.entity.sortImpl;


import com.algoritm.app.entity.algoritm.AbstractSortAlgoritm;
import com.algoritm.app.entity.algoritm.AlgoritmResult;

public class InsertSort extends AbstractSortAlgoritm {

    @Override
    public AlgoritmResult getAlgoritmResult(int[] data) {
        for (int left = 0; left < data.length; left++) {

            int value = data[left];
            // Перемещаемся по элементам, которые перед вытащенным элементом
            int i = left - 1;

            while (i >+ 0) {
                if (value < data[i]) {
                    data[i + 1] = data[i];
                } else {
                    break;
                }
                i--;
            }

            data[i + 1] = value;
        }
        return AlgoritmResult.builder()
                .sortResult(data)
                .build();
    }
}
