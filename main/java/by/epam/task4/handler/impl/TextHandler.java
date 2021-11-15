package by.epam.task4.handler.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.handler.ComponentHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.task4.entity.ElementType.*;

public enum TextHandler implements ComponentHandler {
    INSTANCE;
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAGRAPH_DELIMITER_REGEX = "(?<=[.!?])\\s{4}";
    private final ParagraphHandler paragraphHandler = ParagraphHandler.INSTANCE;

    @Override
    public Component handleRequest(String text) {
        logger.log(Level.INFO, "text to handle -> " + text);
        TextComposite composite = new TextComposite(TEXT);
        String input = text.strip();
        for (String paragraph : input.split(PARAGRAPH_DELIMITER_REGEX)) {
            composite.add(paragraphHandler.handleRequest(paragraph));
        }
        logger.log(Level.INFO, "text composite -> " + composite);
        return composite;
    }
}
