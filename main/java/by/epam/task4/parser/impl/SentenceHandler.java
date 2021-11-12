package by.epam.task4.parser.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.parser.ComponentHandler;

import static by.epam.task4.entity.ElementType.*;

public enum SentenceHandler implements ComponentHandler {
    INSTANCE;
    private final LexemeHandler lexemeHandler = LexemeHandler.INSTANCE;

    @Override
    public Component handleRequest(String sentence) {
        TextComposite composite = new TextComposite(SENTENCE);
        for (String lexeme : sentence.split(LEXEME.getDelimiter())) {
            composite.add(lexemeHandler.handleRequest(lexeme));
        }
        return composite;
    }
}
