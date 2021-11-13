package by.epam.task4.handler.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.entity.ElementType;
import by.epam.task4.exception.CustomException;
import by.epam.task4.io.impl.TextReaderImpl;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TextHandlerTest {

    @Test
    public void testHandleRequest() throws CustomException {
        String text = new TextReaderImpl().read("src/test/resources/text.txt");

        Component component = TextHandler.INSTANCE.handleRequest(text);
        ElementType expected = ElementType.TEXT;
        ElementType actual = component.getElementType();

        assertEquals(actual, expected);
    }
}