package com.algoritm.app.entity.searchImpl;


import com.algoritm.app.entity.algoritm.AbstractSearchAlgoritm;
import com.algoritm.app.entity.algoritm.AlgoritmResult;

public class BinarySearch extends AbstractSearchAlgoritm {

    public BinarySearch(int searchValue) {
        super(searchValue);
    }

    @Override
    public AlgoritmResult getAlgoritmResult(int[] data) {
        int startIndex = 0;
        int endIndex = data.length - 1;
        int middleIndex;
        while (startIndex <= endIndex) {
            middleIndex = startIndex + (endIndex - startIndex) / 2;

            if (data[middleIndex] == searchValue) {
                return AlgoritmResult.builder()
                        .searchResult(middleIndex)
                        .build();
            }

            if (data[middleIndex] > searchValue) {
                endIndex = middleIndex - 1;
            } else {
                startIndex = middleIndex + 1;
            }
        }

        return AlgoritmResult.builder()
                .searchResult(-1)
                .build();
    }
}
