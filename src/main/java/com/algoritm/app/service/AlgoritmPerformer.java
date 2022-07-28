package com.algoritm.app.service;

import com.algoritm.app.entity.algoritm.AlgoritmRequest;
import com.algoritm.app.entity.algoritm.AlgoritmResult;
import com.algoritm.app.entity.algoritm.AlgoritmType;
import com.algoritm.app.exception.DataValidationException;

import java.util.List;

public interface AlgoritmPerformer {

    AlgoritmResult performAlgoritm(AlgoritmType type, AlgoritmRequest algoritmRequest) throws Exception;

    List<AlgoritmResult>  performAlgoritms(List<AlgoritmType> algoritmTypes , AlgoritmRequest algoritmRequest) throws Exception;
}
