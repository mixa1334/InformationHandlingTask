package by.epam.task4.service.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.service.TextService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.epam.task4.entity.ElementType.*;

public class TextServiceImpl implements TextService {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public Optional<Component> sortTextParagraphsByNumberOfSentences(Component textComposite) {
        if (textComposite.getElementType() != TEXT) {
            return Optional.empty();
        }

        Component result = new TextComposite(TEXT);
        List<Component> afterSorting = textComposite.getChildren();
        afterSorting.sort(Comparator.comparingInt(sentence -> sentence.getChildren().size()));
        afterSorting.forEach(element -> result.add(element));

        logger.log(Level.DEBUG, "after sorting -> " + result);

        return Optional.of(result);
    }

    @Override
    public Optional<Component> findSentenceWithLongestWord(Component textComposite) {
        if (textComposite.getElementType() != TEXT && textComposite.getElementType() != PARAGRAPH) {
            return Optional.empty();
        }

        int wordSize = 0;
        Component result = null;
        for (Component component : textComposite.getChildren()) {
            if (component.getElementType() == PARAGRAPH) {
                for (Component sentence : component.getChildren()) {
                    int size = longestWordInSentence(sentence);
                    if (size > wordSize) {
                        result = sentence;
                    }
                }
                continue;
            }
            int size = longestWordInSentence(component);
            if (size > wordSize) {
                result = component;
            }
        }

        return Optional.ofNullable(result);
    }

    @Override
    public int deleteSentencesWithLessWordCount(Component textComposite, int wordCount) {
        int result = 0;
        if (textComposite.getElementType() != TEXT && textComposite.getElementType() != PARAGRAPH) {
            return 0;
        }

        for (Component component : textComposite.getChildren()) {
            if (component.getElementType() == PARAGRAPH) {
                result += deleteSentencesWithLessWordCount(component, wordCount);
                continue;
            }
            int count = countWordsInSentence(component);
            logger.log(Level.DEBUG, "count of words: " + count + " in sentence -> " + component);
            if (count < wordCount) {
                textComposite.remove(component);
                result++;
            }

        }
        return result;
    }

    @Override
    public Optional<Map<Component, Integer>> findAllSameWordsAndTheirCount(Component textComposite) {
        return Optional.empty();
    }

    private int countWordsInSentence(Component component) {
        int count = 0;
        for (Component sentenceComponent : component.getChildren()) {
            if (sentenceComponent.getElementType() == WORD) {
                count++;
            } else if (sentenceComponent.getElementType() == LEXEME) {
                count += countWordsInSentence(sentenceComponent);
            }
        }
        return count;
    }

    private int longestWordInSentence(Component sentence) {
        int wordSize = 0;
        for (Component lexeme : sentence.getChildren()) {
            for (Component element : lexeme.getChildren()) {
                if (element.getElementType() == WORD) {
                    int size = element.getChildren().size();
                    if (size > wordSize) {
                        wordSize = size;
                    }
                }
            }
        }
        return wordSize;
    }
}
