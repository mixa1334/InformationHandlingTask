package by.epam.task4.entity;

public enum ElementType {
    TEXT("\n\n"),
    PARAGRAPH("\n    "),
    SENTENCE(""),
    LEXEME(" "),
    WORD(""),
    EXPRESSION(""),
    SYMBOL("");
    private final String delimiter;

    ElementType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
