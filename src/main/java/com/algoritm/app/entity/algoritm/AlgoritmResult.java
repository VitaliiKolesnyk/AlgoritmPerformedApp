package com.algoritm.app.entity.algoritm;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "algoritm_results")
public class AlgoritmResult {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_generator")
    @SequenceGenerator(name="result_generator", sequenceName = "algoritm_result_sequence", allocationSize=1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "algoritm_type", nullable = false)
    private AlgoritmType algoritmType;

    @Column(name = "inFile", nullable = false)
    private String inFile;

    @Column(name = "values_quantity", nullable = false)
    private int valuesQuantity;

    private String outFile;

    @Column(name = "startTime", nullable = false)
    private LocalDateTime startTime;

    private LocalDateTime stopTime;

    private BigDecimal duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "result", nullable = false, columnDefinition = "FAILED")
    private Result result;

    @Transient
    private int[] sortResult;

    @Transient
    private int searchResult;

    @Tolerate
    public AlgoritmResult() {
    }
}
