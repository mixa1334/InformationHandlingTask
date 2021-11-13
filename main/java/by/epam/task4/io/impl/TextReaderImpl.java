package by.epam.task4.io.impl;

import by.epam.task4.exception.CustomException;
import by.epam.task4.io.TextReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TextReaderImpl implements TextReader {
    @Override
    public String read(String path) throws CustomException {
        try {
            StringBuilder result = new StringBuilder();
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            throw new CustomException("smth wrong with file - " + path, e);
        }
    }
}
