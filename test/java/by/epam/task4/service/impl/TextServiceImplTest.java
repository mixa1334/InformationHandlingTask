package by.epam.task4.service.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.exception.CustomException;
import by.epam.task4.io.impl.TextReaderImpl;
import by.epam.task4.handler.impl.TextHandler;
import by.epam.task4.service.TextService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        Component expected = text.getChildren().get(2);
        Component actual = textService.sortTextParagraphsByNumberOfSentences(text).get().getChildren().get(0);

        assertEquals(actual, expected);
    }

    @Test
    public void testFindSentenceWithLongestWord() {
        Component expected = text.getChildren().get(2).getChildren().get(0);
        Component actual = textService.findSentenceWithLongestWord(text).get();


        assertEquals(actual, expected);
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