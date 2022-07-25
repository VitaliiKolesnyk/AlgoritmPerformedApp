package com.algoritm.app.fileManagement.writer;

import com.algoritm.app.entity.algoritm.AlgoritmResult;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

@Component
public class FileWriterImpl implements FileWriter {

    @Override
    public void write(AlgoritmResult algoritmResult, File outFile) {
        try(BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(outFile))) {
            int [] resultArray = algoritmResult.getSortResult();
            int searchresult = algoritmResult.getSearchResult();
            
            if (resultArray != null) {
                for (int element : resultArray) {
                    writer.write(Integer.toString(element));
                    writer.write("\t");
                }
            } else {
                writer.write(Integer.toString(searchresult));
            }
            
            writer.flush();
        } catch (IOException e) {
            System.out.println("Data cannot be written to file " + outFile.getName());
        }
    }
}
