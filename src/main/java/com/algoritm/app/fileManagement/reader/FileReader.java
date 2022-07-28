package com.algoritm.app.fileManagement.reader;

import com.algoritm.app.exception.FileIsEmptyException;

import java.io.File;

public interface FileReader {
    String read(File inFile) throws FileIsEmptyException;
}
