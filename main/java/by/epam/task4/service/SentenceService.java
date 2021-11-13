package by.epam.task4.service;

import by.epam.task4.entity.Component;

public interface SentenceService {
    int countVowels(Component sentenceComposite);

    int countConsonants(Component sentenceComposite);
}
