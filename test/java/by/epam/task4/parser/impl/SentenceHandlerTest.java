package by.epam.task4.parser.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.parser.ComponentHandler;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SentenceHandlerTest {

    @Test
    public void testHandleRequest() {
        ComponentHandler handler = SentenceHandler.INSTANCE;
        String expected = "Hello world! ";
        Component component = handler.handleRequest(expected);
        String actual = component.convertToString();

        assertEquals(actual, expected);
    }
}