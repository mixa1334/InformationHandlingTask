package by.epam.task4.io.impl;

import by.epam.task4.exception.CustomException;
import by.epam.task4.io.TextReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TextReaderImpl implements TextReader {
    private static final String DELIMITER = " ";

    @Override
    public String read(String path) throws CustomException {
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            StringBuilder result = new StringBuilder();
            lines.forEach(line -> result.append(DELIMITER).append(line));
            return result.toString();
        } catch (IOException e) {
            throw new CustomException("smth wrong with file - " + path, e);
        }
    }
}
