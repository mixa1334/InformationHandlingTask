package by.epam.task4.handler.impl;

import by.epam.task4.entity.Component;
import by.epam.task4.entity.TextLeaf;
import by.epam.task4.handler.ComponentHandler;

public enum SymbolHandler implements ComponentHandler {
    INSTANCE;

    @Override
    public Component handleRequest(String symbol) {
        return new TextLeaf(symbol.charAt(0));
    }
}
