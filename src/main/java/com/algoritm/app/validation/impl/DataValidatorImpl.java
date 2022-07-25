package com.algoritm.app.validation.impl;

import com.algoritm.app.validation.DataValidator;
import org.springframework.stereotype.Component;

@Component
public class DataValidatorImpl implements DataValidator {

    @Override
    public boolean isValid(String line) {
        return line.matches("[\\d\\s\\.,;]*");
    }
}
