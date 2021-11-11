package by.epam.task4.parser;

import by.epam.task4.entity.Component;

public interface ComponentHandler {
    Component handleRequest(String content);
}
