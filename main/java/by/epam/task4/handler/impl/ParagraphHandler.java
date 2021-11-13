package by.epam.task4.handler.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.handler.ComponentHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.task4.entity.ElementType.*;

public enum ParagraphHandler implements ComponentHandler {
    INSTANCE;
    private static final Logger logger = LogManager.getLogger();
    private static final String SENTENCE_REGEX = "(?<=[.!?])\\s+";
    private final SentenceHandler sentenceHandler = SentenceHandler.INSTANCE;

    @Override
    public Component handleRequest(String paragraph) {
        logger.log(Level.INFO, "paragraph handler -> " + paragraph);
        TextComposite composite = new TextComposite(PARAGRAPH);
        String input = paragraph.strip();
        for (String sentence : input.split(SENTENCE_REGEX)) {
            composite.add(sentenceHandler.handleRequest(sentence));
        }
        logger.log(Level.INFO, "paragraph composite -> " + composite);
        return composite;
    }
}
