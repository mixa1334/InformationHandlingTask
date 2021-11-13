package by.epam.task4.service.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.service.TextService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

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
        if (textComposite.getElementType() != TEXT) {
            return Optional.empty();
        }

        int wordSize = 0;
        Component result = null;
        for (Component paragraph : textComposite.getChildren()) {
            for (Component sentence : paragraph.getChildren()) {
                int size = longestWordInSentence(sentence);
                if (size > wordSize) {
                    wordSize = size;
                    result = sentence;
                }
            }
        }

        return Optional.ofNullable(result);
    }

    @Override
    public int deleteSentencesWithLessWordCount(Component textComposite, int wordCount) {
        int result = 0;
        if (textComposite.getElementType() != TEXT) {
            return 0;
        }

        for (Component component : textComposite.getChildren()) {
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
    public Optional<Map<String, Integer>> findAllSameWordsAndTheirCount(Component textComposite) {
        if (textComposite.getElementType() != TEXT) {
            return Optional.empty();
        }

        Map<String, Integer> result = null;

        for (Component paragraph : textComposite.getChildren()) {
            for (Component sentence : paragraph.getChildren()) {
                for (Component lexeme : sentence.getChildren()) {
                    for (Component element : lexeme.getChildren()) {
                        if (element.getElementType() == WORD) {
                            if (result == null) {
                                result = new HashMap<>();
                            }
                            int count = 1;
                            String word = element.convertToString().toLowerCase();
                            if (result.containsKey(word)) {
                                count += result.get(word);
                            }
                            result.put(word, count);
                        }
                    }
                }
            }
        }

        logger.log(Level.DEBUG, "word and their count -> " + result);

        return Optional.ofNullable(result);
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
                    logger.log(Level.DEBUG, "word -> \'" + element.convertToString() + "\' size -> " + size);
                    if (size > wordSize) {
                        wordSize = size;
                    }
                }
            }
        }
        return wordSize;
    }
}
