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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextLeaf textLeaf = (TextLeaf) o;
        if (character != textLeaf.character) return false;
        return elementType == textLeaf.elementType;
    }

    @Override
    public int hashCode() {
        int result = elementType.hashCode();
        result = 31 * result + (int) character;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TextLeaf.class.getSimpleName() + "[", "]")
                .add("elementType=" + elementType)
                .add("character=" + character)
                .toString();
    }
}
