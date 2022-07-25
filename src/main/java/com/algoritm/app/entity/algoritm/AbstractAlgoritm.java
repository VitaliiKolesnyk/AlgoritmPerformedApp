package com.algoritm.app.entity.algoritm;

import lombok.Data;

@Data
public abstract class AbstractAlgoritm {
    private AlgoritmType algoritmType;
    private String inFilePath;
    private String outFilePath;

    public AbstractAlgoritm() {
    }

    public AbstractAlgoritm(AlgoritmType algoritmType, String inFilePath, String outFilePath) {
        this.algoritmType = algoritmType;
        this.inFilePath = inFilePath;
        this.outFilePath = outFilePath;
    }

    public abstract AlgoritmResult getAlgoritmResult(int[] data);

}
