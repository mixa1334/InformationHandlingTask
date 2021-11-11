package by.epam.task4.entity;

import java.util.List;
import java.util.StringJoiner;

public class TextLeaf implements Component {
    private final ElementType elementType;
    private final char character;

    public TextLeaf(char character) {
        elementType = ElementType.SYMBOL;
        this.character = character;
    }

    @Override
    public String convertToString() {
        return String.valueOf(character);
    }

    @Override
    public boolean add(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Component> getChildren() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ElementType getElementType() {
        return elementType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TextLeaf.class.getSimpleName() + "[", "]")
                .add("elementType=" + elementType)
                .add("character=" + character)
                .toString();
    }
}
