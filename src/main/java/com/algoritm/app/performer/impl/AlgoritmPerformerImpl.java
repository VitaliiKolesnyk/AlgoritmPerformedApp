package com.algoritm.app.performer.impl;

import com.algoritm.app.entity.algoritm.*;
import com.algoritm.app.exception.DataValidationException;
import com.algoritm.app.factory.AlgoritmFactory;
import com.algoritm.app.fileManagement.reader.FileReader;
import com.algoritm.app.fileManagement.writer.FileWriter;
import com.algoritm.app.performer.AlgoritmPerformer;
import com.algoritm.app.transformer.DataTransformer;
import com.algoritm.app.validation.DataValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AlgoritmPerformerImpl implements AlgoritmPerformer {

    @Autowired
    private FileReader fileReader;

    @Autowired
    private FileWriter fileWriter;

    @Autowired
    private DataValidator dataValidator;

    @Autowired
    private DataTransformer dataTransformer;

    @Override
    public AlgoritmResult performAlgoritm(AlgoritmType type, AlgoritmRequest algoritmRequest) {
        AlgoritmResult algoritmResult = null;
        LocalDateTime algoritmStart = null;

        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            algoritmStart = LocalDateTime.now();
            log.info("Algoritm {} started.", type);

            String data = fileReader.read(new File(algoritmRequest.getInFile()));
            log.info("Reading data from file {}.", algoritmRequest.getInFile());

            if (!dataValidator.isValid(data)) {
                log.error("Data in file {} is not valid", algoritmRequest.getInFile());
                throw new DataValidationException("Data is not valid");
            }

            int[] transformedArray = dataTransformer.transform(data);

            AbstractAlgoritm algoritm = AlgoritmFactory.createAlgoritm(type, algoritmRequest.getSearchElement());

            algoritmResult = algoritm.getAlgoritmResult(transformedArray);

            fileWriter.write(algoritmResult, new File(algoritmRequest.getOutFile()));
            log.info("Writing result to file {}.", algoritmRequest.getOutFile());

            stopWatch.stop();
            log.info("Total time of {} algoritm performing - {} seconds", type, stopWatch.getTotalTimeSeconds());
            LocalDateTime algoritmEnd = LocalDateTime.now();

            return AlgoritmResult.builder()
                    .algoritmType(type)
                    .inFile(algoritmRequest.getInFile())
                    .valuesQuantity(transformedArray.length)
                    .outFile(algoritmRequest.getOutFile())
                    .startTime(algoritmStart)
                    .stopTime(algoritmEnd)
                    .duration(new BigDecimal(stopWatch.getTotalTimeSeconds()))
                    .result(Result.SUCCESS)
                    .sortResult(algoritmResult.getSortResult())
                    .searchResult(algoritmResult.getSearchResult())
                    .build();

        } catch (Exception e) {
            algoritmResult = AlgoritmResult.builder()
                    .algoritmType(type)
                    .inFile(algoritmRequest.getInFile())
                    .startTime(algoritmStart)
                    .result(Result.FAIL)
                    .build();

            return algoritmResult;
        }
    }

    @Override
    public List<AlgoritmResult> performAlgoritms(List<AlgoritmType> algoritmTypes, AlgoritmRequest algoritmRequest) {
        List<AlgoritmResult> algoritmResults = new ArrayList<>();

        for (AlgoritmType type : algoritmTypes) {
            AlgoritmResult algoritmResult = performAlgoritm(type, algoritmRequest);
            algoritmResults.add(algoritmResult);
        }

        return algoritmResults;
    }
}
