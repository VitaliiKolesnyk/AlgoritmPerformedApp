package com.algoritm.app.service;

import com.algoritm.app.entity.algoritm.AlgoritmResult;

import java.util.List;

public interface AlgoritmService {
    AlgoritmResult saveAlgoritmResult(AlgoritmResult algoritmResult);

    List<AlgoritmResult> saveAlgoritmResults(List<AlgoritmResult> algoritmResults);
}
