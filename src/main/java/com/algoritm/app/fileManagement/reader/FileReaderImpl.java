package com.algoritm.app.fileManagement.reader;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class FileReaderImpl implements FileReader {

    @Override
    public String read(File inFile) {
        StringBuilder builder = new StringBuilder();
        
        try(BufferedReader reader = new BufferedReader(new java.io.FileReader(inFile))) {
            
            while (reader.ready()) {
                builder.append(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File ccanot be found");
        } catch (IOException e) {
            System.out.println("Data from file " + inFile.getName() + " cannot be read");
        } 
        
        return builder.toString();
    }
}
