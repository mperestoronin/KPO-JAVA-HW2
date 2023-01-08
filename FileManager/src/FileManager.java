import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Вспомогательный класс, отвечает за общую работу с файлами
 */
public class FileManager {

    /**
     * директория
     */
    String directory;

    /**
     * конструктор
     * @param dir директория
     */
    FileManager(String dir) {
        directory = dir;
    }

    /**
     * Главный метод, отвечающий за сортировку
     * @return отсортированный список ребер
     */
    public List<Node> getSortedNodes() {
        Graph graph = new Graph(directory);
        Map<Node, List<Node>> adjacencyList = graph.getGraph();
        Sorter sorter = new Sorter(adjacencyList);
        List<Node> sortedNodes = new ArrayList<>();
        try {
            sortedNodes = sorter.sort();
        } catch (IllegalArgumentException | IllegalAccessException e) {
            System.out.println("В зависимости файлов образовался цикл! Некорректные входные данные!");
            System.out.println(e.getMessage());
        }
        return sortedNodes;
    }

    /**
     * Печатает список ребер
     * @param nodes список ребер для печати
     */
    public void printNodes(List<Node> nodes) {
        Collections.reverse(nodes);
        System.out.println("Файлы были отсортированны следующим образом:");
        for (Node node : nodes) {
            if (node.getType()) {
                System.out.println(node);
            }
        }
        System.out.println("----------конец вывода списка файлов----------");
    }

    /**
     * Соединяет содержимое нескольких файлов и выводит результат
     * @param nodes отсортированный список файлов (в том порядке, в котором их надо вывести)
     */
    public void ConcatenateFiles(List<Node> nodes) {
        StringBuilder res = new StringBuilder();
        for (Node node : nodes) {
            try {
                res.append(new String(Files.readAllBytes(Paths.get(node.toString()))));
                res.append("\n");
            } catch (IOException e) {
                System.out.println("Произошла ошибка" + e.getMessage());
            }
        }
        System.out.println("Конкатенированные файлы:\n" + res.toString());
        try (FileWriter writer = new FileWriter("result.txt", false)) {
            writer.write(res.toString());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
