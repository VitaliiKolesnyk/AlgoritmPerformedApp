package com.algoritm.app.controller;

import com.algoritm.app.entity.algoritm.AlgoritmRequest;
import com.algoritm.app.entity.algoritm.AlgoritmResult;
import com.algoritm.app.entity.algoritm.AlgoritmType;
import com.algoritm.app.entity.error.ApiError;
import com.algoritm.app.entity.dto.AlgoritmSearchResultDto;
import com.algoritm.app.entity.dto.AlgoritmSortResultDto;
import com.algoritm.app.exception.FileIsEmptyException;
import com.algoritm.app.exception.IncorrectAlgoritmTypeException;
import com.algoritm.app.service.EmailService;
import com.algoritm.app.util.AlgoritmResultMapper;
import com.algoritm.app.service.AlgoritmPerformer;
import com.algoritm.app.service.AlgoritmService;
import com.algoritm.app.util.AlgoritmUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private EmailService emailService;

    @PostMapping("/sort/{algoritmType}")
    @ApiOperation(value = "Performing of sort algoritm indicated in path")
    public ResponseEntity<AlgoritmSortResultDto> performSortAlgoritm(@PathVariable("algoritmType") String type, @RequestBody AlgoritmRequest algoritmRequest) throws Exception {
        AlgoritmType algoritm = algoritmUtil.getAlgoritmType(type);

        AlgoritmResult algoritmResult = algoritmPerformer.performAlgoritm(algoritm, algoritmRequest);

        service.saveAlgoritmResult(algoritmResult);

        if (algoritmRequest.getEmail() != null) {
            emailService.sendEmail(algoritmRequest.getEmail(), List.of(algoritmResult));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(algoritmResultMapper.toSortDto(algoritmResult));
    }

    @PostMapping("/search/{algoritmType}")
    @ApiOperation(value = "Performing of search algoritm indicated in path")
    public ResponseEntity<AlgoritmSearchResultDto> performSearchAlgoritm(@PathVariable("algoritmType") String type, @RequestBody AlgoritmRequest algoritmRequest) throws Exception {
        AlgoritmType algoritm = algoritmUtil.getAlgoritmType(type);

        AlgoritmResult algoritmResult = algoritmPerformer.performAlgoritm(algoritm, algoritmRequest);

        service.saveAlgoritmResult(algoritmResult);

        if (algoritmRequest.getEmail() != null) {
            emailService.sendEmail(algoritmRequest.getEmail(), List.of(algoritmResult));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(algoritmResultMapper.toSearchDto(algoritmResult));
    }

    @PostMapping("/sortall")
    @ApiOperation(value = "Performing of all sort algoritms")
    public ResponseEntity<List<AlgoritmSortResultDto>> performSortAlgoritms(@RequestBody AlgoritmRequest algoritmRequest) throws Exception {
        List<AlgoritmType> sortAlgoritms = algoritmUtil.getSortAlgoritms();

        List<AlgoritmResult> algoritmResults = algoritmPerformer.performAlgoritms(sortAlgoritms, algoritmRequest);

        service.saveAlgoritmResults(algoritmResults);

        if (algoritmRequest.getEmail() != null) {
            emailService.sendEmail(algoritmRequest.getEmail(), algoritmResults);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(algoritmResultMapper.toSortDtoList(algoritmResults));
    }

    @PostMapping("/searchall")
    @ApiOperation(value = "Performing of all search algoritms")
    public ResponseEntity<List<AlgoritmSearchResultDto>> performSearchAlgoritms(@RequestBody AlgoritmRequest algoritmRequest) throws Exception {
        List<AlgoritmType> sortAlgoritms = algoritmUtil.getSearchAlgoritms();

        List<AlgoritmResult> algoritmResults = algoritmPerformer.performAlgoritms(sortAlgoritms, algoritmRequest);

        service.saveAlgoritmResults(algoritmResults);

        if (algoritmRequest.getEmail() != null) {
            emailService.sendEmail(algoritmRequest.getEmail(), algoritmResults);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(algoritmResultMapper.toSearchDtoList(algoritmResults));
    }

    @ExceptionHandler(value = {IncorrectAlgoritmTypeException.class, FileIsEmptyException.class})
    public ResponseEntity<ApiError> handleIncorrectAlgoritmTypeException(Exception e) {
        return ResponseEntity.badRequest()
                .body(new ApiError(e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return errorMap;
    }
}
