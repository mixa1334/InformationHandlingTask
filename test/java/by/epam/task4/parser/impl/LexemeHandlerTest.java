package by.epam.task4.parser.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.parser.ComponentHandler;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LexemeHandlerTest {
    ComponentHandler handler = LexemeHandler.INSTANCE;

    @Test
    public void testHandleRequest() {
        String expected = "Hello,";
        Component component = handler.handleRequest(expected);
        String actual = component.convertToString();

        assertEquals(actual, expected);
    }

    @Test
    public void testHandleRequestWord() {
        String input = "(\"Hello\",";
        Component lexeme = handler.handleRequest(input);
        Component word = lexeme.getChildren().get(2);

        String expected = "Hello";
        String actual = word.convertToString();

        assertEquals(actual, expected);
    }

    @Test
    public void testHandleRequestExpression() {
        String input = "a==b?a:b";
        Component lexeme = handler.handleRequest(input);
        Component expression = lexeme.getChildren().get(0);

        String expected = "a==b?a:b";
        String actual = expression.convertToString();

        assertEquals(actual, expected);
    }
}