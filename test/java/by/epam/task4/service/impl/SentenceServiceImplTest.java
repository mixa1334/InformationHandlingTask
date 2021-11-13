package by.epam.task4.service.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.handler.impl.SentenceHandler;
import by.epam.task4.service.SentenceService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SentenceServiceImplTest {
    Component sentence;
    SentenceService sentenceService;

    @BeforeClass
    public void setUp() {
        sentence = SentenceHandler.INSTANCE.handleRequest("It is a long a!=b, fact: (Привет).");
        sentenceService = new SentenceServiceImpl();
    }

    @Test
    public void testCountVowels() {
        int expected = 8;
        int actual = sentenceService.countVowels(sentence);

        assertEquals(actual, expected);
    }

    @Test
    public void testCountConsonants() {
        int expected = 13;
        int actual = sentenceService.countConsonants(sentence);

        assertEquals(actual, expected);
    }
}