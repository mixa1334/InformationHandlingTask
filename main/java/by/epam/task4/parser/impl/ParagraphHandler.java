package by.epam.task4.parser.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.parser.ComponentHandler;

import static by.epam.task4.entity.ElementType.*;

public enum ParagraphHandler implements ComponentHandler {
    INSTANCE;
    private static final String SENTENCE_REGEX = "(?<=[.!?])\\s+";
    private final SentenceHandler sentenceHandler = SentenceHandler.INSTANCE;

    @Override
    public Component handleRequest(String paragraph) {
        TextComposite composite = new TextComposite(PARAGRAPH);
        String input = paragraph.strip();
        for (String sentence : input.split(SENTENCE_REGEX)) {
            composite.add(sentenceHandler.handleRequest(sentence));
        }
        return composite;
    }
}
