package by.epam.task4.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TextComposite implements Component {
    private final ElementType elementType;
    private final List<Component> components;

    public TextComposite(ElementType element) {
        this.elementType = element;
        components = new ArrayList<>();
    }

    @Override
    public String convertToString() {
        StringBuilder text = new StringBuilder();
        for (Component component : components) {
            if (component.getElementType() == ElementType.PARAGRAPH) {
                text.append(component.getElementType().getDelimiter());
                text.append(component.convertToString());
            } else {
                text.append(component.convertToString());
                text.append(component.getElementType().getDelimiter());
            }
        }
        return text.toString();
    }

    @Override
    public boolean add(Component component) {
        return components.add(component);
    }

    @Override
    public boolean remove(Component component) {
        return components.remove(component);
    }

    @Override
    public List<Component> getChildren() {
        return components;
    }

    @Override
    public ElementType getElementType() {
        return elementType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TextComposite.class.getSimpleName() + "[", "]")
                .add("elementType=" + elementType)
                .add("components=" + components)
                .toString();
    }
}
