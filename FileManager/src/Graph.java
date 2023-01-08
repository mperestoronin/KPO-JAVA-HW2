import java.io.File;
import java.util.*;

/**
 * представляем зависимоть фацлов в виде графа
 */
public final class Graph {
    /**
     * список смежности
     */
    private final Map<Node, List<Node>> adjacencyList;
    /**
     * директория
     */
    private final String dir;

    /**
     * конструктор
     * @param directory директория
     */
    public Graph(String directory) {
        dir = directory;
        adjacencyList = new LinkedHashMap<>();
    }

    /**
     * Представляем наш граф в виде списка смежности
     * @return список смежности
     */
    public Map<Node, List<Node>> getGraph() {
        List<Node> files;
        files = TraverseFiles.traverse(new File(dir));
        for (Node file : files) {
            List<Node> list = FileChecker.ScanFiles(file, dir);
            adjacencyList.put(file, list);
        }
        return adjacencyList;
    }
}
