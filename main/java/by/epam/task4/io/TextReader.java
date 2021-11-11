package by.epam.task4.io;

import by.epam.task4.exception.CustomException;

public interface TextReader {
    String read(String path) throws CustomException;
}