package com.algoritm.app.fileManagement.reader;

import com.algoritm.app.exception.FileIsEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
@Component
public class FileReaderImpl implements FileReader {

    @Override
    public String read(File inFile) throws FileIsEmptyException {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(inFile))) {

            while (reader.ready()) {
                builder.append(reader.readLine());
            }

        } catch (FileNotFoundException e) {
            log.info("File {} cannot be found", inFile.getAbsolutePath());
            throw new FileIsEmptyException("File is empty or does not exist");
        } catch (IOException e) {
            log.info("Data from file {} cannot be read", inFile.getAbsolutePath());
        }

        return builder.toString();
    }
}
