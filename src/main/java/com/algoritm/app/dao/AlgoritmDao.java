package com.algoritm.app.dao;

import com.algoritm.app.entity.algoritm.AlgoritmResult;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AlgoritmDao extends BaseDao {
    protected AlgoritmDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Transactional
    public AlgoritmResult saveAlgoritmResult(AlgoritmResult algoritmResult) {
        getSession().save(algoritmResult);

        return algoritmResult;
    }

    @Transactional
    public List<AlgoritmResult> saveAlgoritmResults(List<AlgoritmResult> algoritmResults) {
        algoritmResults.forEach(algoritmResult -> getSession().save(algoritmResult));

        return algoritmResults;
    }
}
