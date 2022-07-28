package com.algoritm.app.factory;


import com.algoritm.app.entity.algoritm.AbstractAlgoritm;
import com.algoritm.app.entity.algoritm.AlgoritmType;
import com.algoritm.app.entity.searchImpl.BinarySearch;
import com.algoritm.app.entity.searchImpl.LinearSearch;
import com.algoritm.app.entity.sortImpl.*;
import org.springframework.stereotype.Component;

@Component
public class AlgoritmFactory {
    
    public AbstractAlgoritm createAlgoritm(AlgoritmType type, int searchElement) {
        AbstractAlgoritm algoritm = null;
        
        switch (type) {
            case BUBBLE_SORT: algoritm = new BubbleSort();
            break;
            case SELECT_SORT: algoritm = new SelectSort();
            break;
            case BINARY_SEARCH: algoritm = new BinarySearch(searchElement);
            break;
            case MERGE_SORT: algoritm = new MergeSort();
            break;
            case INSERT_SORT: algoritm = new InsertSort();
            break;
            case QUICK_SORT: algoritm = new QuickSort();
            break;
            case LINEAR_SEARCH: algoritm = new LinearSearch(searchElement);
            break;
        }
        
        return algoritm;
    }
}
