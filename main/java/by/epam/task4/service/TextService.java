package by.epam.task4.service;

import by.epam.task4.entity.Component;

import java.util.Map;
import java.util.Optional;

public interface TextService {
    Optional<Component> sortTextParagraphsByNumberOfSentences(Component textComposite);

    Optional<Component> findSentenceWithLongestWord(Component component);

    int deleteSentencesWithLessWordCount(Component textComposite, int wordCount);

    Optional<Map<String, Integer>> findAllSameWordsAndTheirCount(Component textComposite);
}
