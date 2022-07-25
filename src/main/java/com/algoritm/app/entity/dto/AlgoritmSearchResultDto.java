package com.algoritm.app.entity.dto;

import com.algoritm.app.entity.algoritm.AlgoritmType;
import com.algoritm.app.entity.algoritm.Result;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AlgoritmSearchResultDto {
    private Long id;
    private AlgoritmType algoritmType;
    private String inFile;
    private int valuesQuantity;
    private String outFile;
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private BigDecimal duration;
    private Result result;
    private int searchResult;
}
