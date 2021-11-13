package by.epam.task4.handler.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.handler.ComponentHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.task4.entity.ElementType.*;

public enum SentenceHandler implements ComponentHandler {
    INSTANCE;
    private static final Logger logger = LogManager.getLogger();
    private final LexemeHandler lexemeHandler = LexemeHandler.INSTANCE;

    @Override
    public Component handleRequest(String sentence) {
        logger.log(Level.INFO, "sentence to handle -> " + sentence);
        TextComposite composite = new TextComposite(SENTENCE);
        for (String lexeme : sentence.split(LEXEME.getDelimiter())) {
            composite.add(lexemeHandler.handleRequest(lexeme));
        }
        logger.log(Level.INFO, "sentence composite -> " + composite);
        return composite;
    }
}
