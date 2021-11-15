package by.epam.task4.handler.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.handler.ComponentHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.task4.entity.ElementType.*;

public enum LexemeHandler implements ComponentHandler {
    INSTANCE;
    private static final String WORD_REGEX = "(?U)(^\\p{Punct}*)(\\w[\\w-']*)(\\p{Punct}*$)";
    private static final String SENTENCE_PUNCTUATION_REGEX = "\\.{1,3}|[?!,;:]$";
    private static final String EMPTY_CHARACTER = "";
    private final SymbolHandler symbolHandler = SymbolHandler.INSTANCE;
    private final WordHandler wordHandler = WordHandler.INSTANCE;
    private final ExpressionHandler expressionHandler = ExpressionHandler.INSTANCE;

    @Override
    public Component handleRequest(String lexeme) {
        TextComposite composite = new TextComposite(LEXEME);
        String input = lexeme.strip();
        Matcher wordMatcher = Pattern.compile(WORD_REGEX).matcher(input);

        if (wordMatcher.find()) {
            int countOfGroups = 3;
            int wordGroup = 2;
            for (int i = 1; i <= countOfGroups; i++) {
                if (i == wordGroup) {
                    String word = wordMatcher.group(i);
                    composite.add(wordHandler.handleRequest(word));
                    continue;
                }
                String punctuation = wordMatcher.group(i);
                if (!punctuation.isEmpty()) {
                    for (String symbol : punctuation.split(SYMBOL.getDelimiter())) {
                        composite.add(symbolHandler.handleRequest(symbol));
                    }
                }
            }
        } else {
            Matcher sentencePunctuationMatcher = Pattern.compile(SENTENCE_PUNCTUATION_REGEX).matcher(input);
            String expression = input;
            if (sentencePunctuationMatcher.find()) {
                String[] punctuation = sentencePunctuationMatcher.group().split(SYMBOL.getDelimiter());
                for (String symbol : punctuation) {
                    composite.add(symbolHandler.handleRequest(symbol));
                }
                input = expression.replaceAll(SENTENCE_PUNCTUATION_REGEX, EMPTY_CHARACTER);
            }
            composite.add(expressionHandler.handleRequest(input));
        }

        return composite;
    }
}
