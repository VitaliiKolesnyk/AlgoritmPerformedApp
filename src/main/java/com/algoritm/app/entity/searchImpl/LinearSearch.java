package com.algoritm.app.entity.searchImpl;


import com.algoritm.app.entity.algoritm.AlgoritmResult;
import com.algoritm.app.entity.algoritm.AbstractSearchAlgoritm;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LinearSearch extends AbstractSearchAlgoritm {

    public LinearSearch(int searchValue) {
        super(searchValue);
    }

    @Override
    public AlgoritmResult getAlgoritmResult(int[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == searchValue) {
                return AlgoritmResult.builder()
                        .searchResult(i)
                        .build();
            }
        }

        return AlgoritmResult.builder()
                .searchResult(-1)
                .build();
    }
}
