package by.epam.task4.service.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.service.SentenceService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.task4.entity.ElementType.*;

public class SentenceServiceImpl implements SentenceService {
    private final static Logger logger = LogManager.getLogger();
    private final static String VOWEL_REGEX = "^[aeiouyAEIOUYауоыиэяюёеАУОЫИЭЯЮЕЁ]$";
    private final static String CONSONANT_REGEX = "^[bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZбвгджзйклмнпрстфхцчшщБВГДЖЗЙКЛМНПРСТФХЦЧШЩ]$";

    @Override
    public int countVowels(Component sentenceComposite) {
        if (sentenceComposite.getElementType() != SENTENCE) {
            return 0;
        }
        return countLetters(sentenceComposite, VOWEL_REGEX);
    }

    @Override
    public int countConsonants(Component sentenceComposite) {
        if (sentenceComposite.getElementType() != SENTENCE) {
            return 0;
        }
        return countLetters(sentenceComposite, CONSONANT_REGEX);
    }

    private int countLetters(Component text, String regex) {
        int result = 0;
        for (Component component : text.getChildren()) {
            if (component.getElementType() != SYMBOL) {
                result += countLetters(component, regex);
                continue;
            }
            String symbol = component.convertToString();
            if (symbol.matches(regex)) {
                logger.log(Level.DEBUG, "symbol -> " + symbol);
                result++;
            }
        }
        return result;
    }
}
