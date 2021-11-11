package by.epam.task4.entity;

import java.util.List;

public interface Component {
    String convertToString();

    boolean add(Component component);

    boolean remove(Component component);

    List<Component> getChildren();

    ElementType getElementType();
}
