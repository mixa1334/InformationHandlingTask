package by.epam.task4.parser.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.entity.TextComposite;
import by.epam.task4.parser.ComponentHandler;

import static by.epam.task4.entity.ElementType.*;

public enum ExpressionHandler implements ComponentHandler {
    INSTANCE;
    private final SymbolHandler symbolHandler = SymbolHandler.INSTANCE;

    @Override
    public Component handleRequest(String expression) {
        TextComposite composite = new TextComposite(EXPRESSION);
        for (String character : expression.split(SYMBOL.getDelimiter())) {
            Component symbol = symbolHandler.handleRequest(character);
            composite.add(symbol);
        }
        return composite;
    }
}
