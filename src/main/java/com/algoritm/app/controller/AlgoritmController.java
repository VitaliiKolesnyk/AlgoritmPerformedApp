package com.algoritm.app.controller;

import com.algoritm.app.entity.algoritm.AlgoritmRequest;
import com.algoritm.app.entity.algoritm.AlgoritmResult;
import com.algoritm.app.entity.algoritm.AlgoritmType;
import com.algoritm.app.entity.error.ApiError;
import com.algoritm.app.entity.dto.AlgoritmSearchResultDto;
import com.algoritm.app.entity.dto.AlgoritmSortResultDto;
import com.algoritm.app.exception.DataValidationException;
import com.algoritm.app.exception.IncorrectAlgoritmTypeException;
import com.algoritm.app.mapper.AlgoritmResultMapper;
import com.algoritm.app.performer.AlgoritmPerformer;
import com.algoritm.app.service.AlgoritmService;
import com.algoritm.app.util.AlgoritmUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/algoritms")
public class AlgoritmController {

    @Autowired
    private AlgoritmService service;

    @Autowired
    private AlgoritmUtil algoritmUtil;

    @Autowired
    private AlgoritmPerformer algoritmPerformer;

    @Autowired
    private AlgoritmResultMapper algoritmResultMapper;

    @PostMapping("/sort/{algoritmType}")
    @ApiOperation(value = "Performing of sort algoritm indicated in path")
    public ResponseEntity<AlgoritmSortResultDto> performSortAlgoritm(@PathVariable("algoritmType") String type, @RequestBody AlgoritmRequest algoritmRequest) throws DataValidationException, IncorrectAlgoritmTypeException {
        AlgoritmType algoritm = algoritmUtil.getAlgoritmType(type);

        AlgoritmResult algoritmResult = algoritmPerformer.performAlgoritm(algoritm, algoritmRequest);

        service.saveAlgoritmResult(algoritmResult);

        return ResponseEntity.status(HttpStatus.OK)
                .body(algoritmResultMapper.toSortDto(algoritmResult));
    }

    @PostMapping("/search/{algoritmType}")
    @ApiOperation(value = "Performing of search algoritm indicated in path")
    public ResponseEntity<AlgoritmSearchResultDto> performSearchAlgoritm(@PathVariable("algoritmType") String type, @RequestBody AlgoritmRequest algoritmRequest) throws IncorrectAlgoritmTypeException, DataValidationException {
        AlgoritmType algoritm = algoritmUtil.getAlgoritmType(type);

        AlgoritmResult algoritmResult = algoritmPerformer.performAlgoritm(algoritm, algoritmRequest);

        service.saveAlgoritmResult(algoritmResult);

        return ResponseEntity.status(HttpStatus.OK)
                .body(algoritmResultMapper.toSearchDto(algoritmResult));
    }

    @PostMapping("/sortall")
    @ApiOperation(value = "Performing of all sort algoritms")
    public ResponseEntity<List<AlgoritmSortResultDto>> performSortAlgoritms(@RequestBody AlgoritmRequest algoritmRequest) throws DataValidationException {
        List<AlgoritmType> sortAlgoritms = algoritmUtil.getSortAlgoritms();

        List<AlgoritmResult> algoritmResults = algoritmPerformer.performAlgoritms(sortAlgoritms, algoritmRequest);

        service.saveAlgoritmResults(algoritmResults);

        return ResponseEntity.status(HttpStatus.OK)
                .body(algoritmResultMapper.toSortDtoList(algoritmResults));
    }

    @PostMapping("/searchall")
    @ApiOperation(value = "Performing of all search algoritms")
    public ResponseEntity<List<AlgoritmSearchResultDto>> performSearchAlgoritms(@RequestBody AlgoritmRequest algoritmRequest) throws DataValidationException {
        List<AlgoritmType> sortAlgoritms = algoritmUtil.getSearchAlgoritms();

        List<AlgoritmResult> algoritmResults = algoritmPerformer.performAlgoritms(sortAlgoritms, algoritmRequest);

        service.saveAlgoritmResults(algoritmResults);

        return ResponseEntity.status(HttpStatus.OK)
                .body(algoritmResultMapper.toSearchDtoList(algoritmResults));
    }

    @ExceptionHandler(value = IncorrectAlgoritmTypeException.class)
    public ResponseEntity<ApiError> handleIncorrectAlgoritmTypeException(IncorrectAlgoritmTypeException e) {
        return ResponseEntity.badRequest()
                .body(new ApiError(e.getMessage()));
    }
}
