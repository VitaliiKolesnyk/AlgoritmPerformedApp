package com.algoritm.app.entity.algoritm;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class AlgoritmRequest {
    @Pattern(regexp = "([a-zA-Z]:)?(/[a-zA-Z0-9._-]+)+/?", message = "Please provide valid File path")
    private String inFile;
    @Pattern(regexp = "([a-zA-Z]:)?(/[a-zA-Z0-9._-]+)+/?", message = "Please provide valid File path")
    private String outFile;
    @Email(message = "Not correct email")
    private String email;
    private Integer searchElement;
}
