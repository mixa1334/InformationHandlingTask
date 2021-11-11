package by.epam.task4.parser.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.parser.ComponentHandler;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class WordHandlerTest {

    @Test
    public void testHandleRequest() {
        ComponentHandler handler = WordHandler.INSTANCE;

        String expected = "hello";

        Component word = handler.handleRequest(expected);

        String actual = word.convertToString();

        assertEquals(actual, expected);
    }
}