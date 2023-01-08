import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Нода представляет собой папку/файл
 */
public class Node implements Comparable<Node> {
    /**
     * тип вершины (является ли она файлом)
     */
    private final boolean type;
    /**
     * путь
     */
    private final String name;

    /**
     * гет акксессор для типа ноды
     * @return тип ноды
     */
    public boolean getType() {
        return type;
    }

    /**
     * конструктор
     */
    Node() {
        type = false;
        name = null;
    }

    /**
     * конструктор
     */
    Node(String name) {
        this.type = true;
        this.name = name;
    }

    /**
     * переводит типо Node в строку
     *
     * @return Node в виде string
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * нужнен для сравнения двух нод
     *
     * @param obj другая нода, с которой мы сравниваем
     * @return какая нода больше
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Node node = (Node) obj;
        return type == node.type && Objects.equals(name, node.name);
    }

    /**
     * получаем хэш код
     *
     * @return хэш код
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }

    /**
     * нужен для сравнения двух нод
     *
     * @param o the object to be compared.
     * @return какая нода больше
     */
    @Override
    public int compareTo(@NotNull Node o) {
        return (Objects.requireNonNull(name).compareTo(Objects.requireNonNull(o.name)));
    }
}
