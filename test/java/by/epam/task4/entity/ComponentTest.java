package by.epam.task4.entity;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ComponentTest {

    @Test
    public void testConvertToString() {
        TextLeaf h = new TextLeaf('H');
        TextLeaf e = new TextLeaf('e');
        TextLeaf l = new TextLeaf('l');
        TextLeaf o = new TextLeaf('o');
        TextLeaf comma = new TextLeaf(',');
        TextLeaf w = new TextLeaf('w');
        TextLeaf r = new TextLeaf('r');
        TextLeaf d = new TextLeaf('d');
        TextLeaf exclpoint = new TextLeaf('!');

        TextComposite lexeme1 = new TextComposite(ElementType.LEXEME);
        TextComposite word1 = new TextComposite(ElementType.WORD);
        word1.add(h);
        word1.add(e);
        word1.add(l);
        word1.add(l);
        word1.add(o);
        lexeme1.add(word1);
        lexeme1.add(comma);

        TextComposite lexeme2 = new TextComposite(ElementType.LEXEME);
        TextComposite word2 = new TextComposite(ElementType.WORD);
        word2.add(w);
        word2.add(o);
        word2.add(r);
        word2.add(l);
        word2.add(d);
        lexeme2.add(word2);
        lexeme2.add(exclpoint);

        TextComposite sentence = new TextComposite(ElementType.SENTENCE);
        sentence.add(lexeme1);
        sentence.add(lexeme2);

        TextComposite paragraph = new TextComposite(ElementType.PARAGRAPH);
        paragraph.add(sentence);
        paragraph.add(sentence);

        TextComposite text1 = new TextComposite(ElementType.TEXT);
        text1.add(paragraph);

        String expected = "\n    Hello, world! Hello, world! ";
        String actual = text1.convertToString();

        assertEquals(actual, expected);
    }
}