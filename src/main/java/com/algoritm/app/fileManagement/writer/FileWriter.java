package com.algoritm.app.fileManagement.writer;


import com.algoritm.app.entity.algoritm.AlgoritmResult;

import java.io.File;

public interface FileWriter {
    void write(AlgoritmResult algoritmResult, File outFile);
}
