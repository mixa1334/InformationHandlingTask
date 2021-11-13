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

    public boolean isValidComponentToAdd(ElementType type) {
        switch (this) {
            case TEXT -> {
                return type == PARAGRAPH;
            }
            case PARAGRAPH -> {
                return type == SENTENCE;
            }
            case SENTENCE -> {
                return type == LEXEME;
            }
            case LEXEME -> {
                return type == WORD || type == EXPRESSION || type == SYMBOL;
            }
            case WORD, EXPRESSION -> {
                return type == SYMBOL;
            }
            default -> {
                return false;
            }
        }
    }
}
