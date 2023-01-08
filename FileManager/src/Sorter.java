import java.util.*;

/**
 * Вспомогательный класс, отвечающий за сортировку файлов
 */
class Sorter {
    /**
     * стэк для хранения посещенных узлов в топологическом порядке
     */
    static Stack<Node> visitedVertices = new Stack<>();

    /**
     * Для хранения топологического порядка
     */
    static ArrayList<Node> topOrder = new ArrayList<>();

    /**
     * Список смежности для хранения ребер
     */
    static Map<Node, List<Node>> adjacencyList;
    /**
     * Храним информацию, обходили ли мы уже ту ноду или нет
     */
    static Map<Node, Boolean> passed = new HashMap<>();

    /**
     * конструктор
     *
     * @param adj список смежности
     */
    public Sorter(Map<Node, List<Node>> adj) {
        adjacencyList = adj;
        for (Node key : adjacencyList.keySet()) {
            passed.put(key, false);
        }
    }

    /**
     * Главная функция класса, отвечает за вызов других функций
     * @return список нод в топологическом порядке
     * @throws IllegalAccessException на случай, если образуется список зависимостей
     */
    public List<Node> sort() throws IllegalAccessException {
        for (Node key : adjacencyList.keySet()) {
            if (!passed.get(key)) {
                dfs(key);
            }
        }
        if (check_cycle()) {
            throw new IllegalAccessException("Зависимые файлы образуют цикл");
        }
        return topOrder;
    }

    /**
     * обход графа зависимостей
     * @param node начало
     */
    private void dfs(Node node) {
        passed.put(node, true);
        List<Node> list = adjacencyList.get(node);
        if (list != null) {
            for (Node depNode : list) {
                if (!passed.get(depNode)) {
                    dfs(depNode);
                }
            }
        }
        visitedVertices.push(node);
    }

    /**
     * Функция для проверки на наличие циклимости в зависимости файлов
     * @return есть ли циклимость в зависимости файлов
     */
    static boolean check_cycle() {
        Map<Node, Integer> pos = new HashMap<>();

        int i = 0;
        while (!visitedVertices.isEmpty()) {
            pos.put(visitedVertices.peek(), i);
            topOrder.add(visitedVertices.peek());
            i++;
            visitedVertices.pop();
        }
        for (Node node : adjacencyList.keySet()) {
            for (Node node1 : adjacencyList.get(node)) {
                if (pos.get(node) > pos.get(node1)) {
                    return true;
                }
            }
        }
        return false;
    }
}
 