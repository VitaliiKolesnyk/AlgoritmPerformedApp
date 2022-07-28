package com.algoritm.app.service;

import com.algoritm.app.entity.algoritm.AlgoritmResult;

import java.util.List;

public interface EmailService {
    void sendEmail(String emailTo, List<AlgoritmResult> algoritmResult);
}
