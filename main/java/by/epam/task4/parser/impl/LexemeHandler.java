package by.epam.task4.parser.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.parser.ComponentHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.task4.entity.ElementType.*;

public enum LexemeHandler implements ComponentHandler {
    INSTANCE;
    private static final Logger logger = LogManager.getLogger();
    private static final String WORD_REGEX = "(?U)(^\\p{Punct}*)([\\w-]+)(\\p{Punct}*$)";
    private static final String SENTENCE_PUNCTUATION_REGEX = "\\.{1,3}|[?!,;:]$";
    private final SymbolHandler symbolHandler = SymbolHandler.INSTANCE;
    private final WordHandler wordHandler = WordHandler.INSTANCE;
    private final ExpressionHandler expressionHandler = ExpressionHandler.INSTANCE;

    @Override
    public Component handleRequest(String lexeme) {
        logger.log(Level.INFO, "input lexeme -> " + lexeme);
        TextComposite composite = new TextComposite(LEXEME);
        String input = lexeme.strip();
        Matcher wordMatcher = Pattern.compile(WORD_REGEX).matcher(input);
        if (wordMatcher.find()) {
            String[] punctuationAtBeginning = wordMatcher.group(1).split(SYMBOL.getDelimiter());
            for (String symbol : punctuationAtBeginning) {
                composite.add(symbolHandler.handleRequest(symbol));
            }

            String word = wordMatcher.group(2);
            composite.add(wordHandler.handleRequest(word));

            String[] punctuationAtEnd = wordMatcher.group(3).split(SYMBOL.getDelimiter());
            for (String symbol : punctuationAtEnd) {
                composite.add(symbolHandler.handleRequest(symbol));
            }
        } else {
            Matcher sentencePunctuationMatcher = Pattern.compile(SENTENCE_PUNCTUATION_REGEX).matcher(input);
            String expression = input;
            if (sentencePunctuationMatcher.find()) {
                String[] punctuation = sentencePunctuationMatcher.group().split(SYMBOL.getDelimiter());
                for (String symbol : punctuation) {
                    composite.add(symbolHandler.handleRequest(symbol));
                }
                input = expression.replaceAll(SENTENCE_PUNCTUATION_REGEX, "");
            }
            composite.add(expressionHandler.handleRequest(input));
        }
        logger.log(Level.INFO, "output composite (lexeme) -> " + composite);
        return composite;
    }
}
