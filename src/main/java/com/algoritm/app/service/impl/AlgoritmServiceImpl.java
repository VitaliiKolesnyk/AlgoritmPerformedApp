package com.algoritm.app.service.impl;

import com.algoritm.app.dao.AlgoritmDao;
import com.algoritm.app.entity.algoritm.AlgoritmResult;
import com.algoritm.app.service.AlgoritmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlgoritmServiceImpl implements AlgoritmService {

    @Autowired
    private AlgoritmDao algoritmDao;

    @Override
    public AlgoritmResult saveAlgoritmResult(AlgoritmResult algoritmResult) {
        return algoritmDao.saveAlgoritmResult(algoritmResult);
    }

    public List<AlgoritmResult> saveAlgoritmResults(List<AlgoritmResult> algoritmResults) {
        return algoritmDao.saveAlgoritmResults(algoritmResults);
    }
}
