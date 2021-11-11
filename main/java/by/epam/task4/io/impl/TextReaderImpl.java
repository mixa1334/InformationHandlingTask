package by.epam.task4.io.impl;

import by.epam.task4.exception.CustomException;
import by.epam.task4.io.TextReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextReaderImpl implements TextReader {
    @Override
    public String read(String path) throws CustomException {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            throw new CustomException("smth wrong with file - " + path, e);
        }
    }
}
