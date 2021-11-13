package by.epam.task4.service.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.exception.CustomException;
import by.epam.task4.io.impl.TextReaderImpl;
import by.epam.task4.handler.impl.TextHandler;
import by.epam.task4.service.TextService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class TextServiceImplTest {
    Component text;
    TextService textService;

    @BeforeMethod
    public void setUp() throws CustomException {
        String input = new TextReaderImpl().read("src/test/resources/text.txt");
        text = TextHandler.INSTANCE.handleRequest(input);
        textService = new TextServiceImpl();
    }

    @Test
    public void testSortTextParagraphsByNumberOfSentences() {
        List<Component> children = text.getChildren();
        Component expected = children.get(children.size() - 2);
        children = textService.sortTextParagraphsByNumberOfSentences(text).get().getChildren();
        Component actual = children.get(0);

        assertEquals(actual, expected);
    }

    @Test
    public void testFindSentenceWithLongestWord() {
        List<Component> children = text.getChildren();
    }

    @Test
    public void testDeleteSentencesWithLessWordCount() throws CustomException {
        int expected = 2;
        int wordCount = 18;
        int actual = textService.deleteSentencesWithLessWordCount(text, wordCount);

        assertEquals(actual, expected);
    }

    @Test
    public void testFindAllSameWordsAndTheirCount() {
    }
}