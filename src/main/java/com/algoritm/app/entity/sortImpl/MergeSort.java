package com.algoritm.app.entity.sortImpl;


import com.algoritm.app.entity.algoritm.AbstractSortAlgoritm;
import com.algoritm.app.entity.algoritm.AlgoritmResult;

public class MergeSort extends AbstractSortAlgoritm {

    public AlgoritmResult mergeArray(int [] src1, int [] src2) {
        int index1 = 0;
        int index2 = 0;
        int size = src1.length + src2.length;

        int [] dest = new int[size];

        if(src1.length == 1 & src2.length == 1) {
            if(src1[0] < src2[0]) {
                dest[0] = src1[0];
                dest[1] = src2[0];
            } else if (src1[0] > src2[0]) {
                dest[0] = src2[0];
                dest[1] = src1[0];
            }
        }

        if(src1.length > 1 || src2.length > 1) {
            for (int i = 0; i < size; i++) {
                if(index1 == src1.length) {
                    dest[i] = src2[i - index1];
                    index2++;
                } else if(index2 == src2.length) {
                    dest[i] = src1[i - index2];
                    index1++;
                } else if (index1 < src1.length & index2 < src2.length & src1[index1] < src2[index2]) {
                    dest[i] = src1[index1];
                    index1++;
                } else if (index1 < src1.length & index2 < src2.length & src1[index1] > src2[index2]) {
                    dest[i] = src2[index2];
                    index2++;

                }
            }
        }

        return AlgoritmResult.builder()
                .sortResult(dest)
                .build();
    }

    @Override
    public AlgoritmResult getAlgoritmResult(int[] data) {
        if (data.length < 2) {
            return AlgoritmResult.builder()
                    .sortResult(data)
                    .build();
        }

        int [] arrayB = new int[data.length / 2];
        System.arraycopy(data, 0, arrayB, 0, data.length / 2);

        int [] arrayC = new int[data.length - data.length / 2];
        System.arraycopy(data, data.length / 2, arrayC, 0, data.length - data.length / 2);

        arrayB = getAlgoritmResult(arrayB).getSortResult();
        arrayC = getAlgoritmResult(arrayC).getSortResult();

        return mergeArray(arrayB, arrayC);
    }
}
