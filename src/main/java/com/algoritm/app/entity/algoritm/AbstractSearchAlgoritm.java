package com.algoritm.app.entity.algoritm;

import com.algoritm.app.entity.algoritm.AbstractAlgoritm;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class AbstractSearchAlgoritm extends AbstractAlgoritm {
    protected int searchValue;

    public AbstractSearchAlgoritm(int searchValue) {
        this.searchValue = searchValue;
    }
}
