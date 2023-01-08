import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class TraverseFiles {
    /**
     * Проходимся по всем файлам в папке и по всем файлам в папках, вложенных в корневую папку
     * @param rootFile путь к папке/файлу
     * @return список нод
     */
    public static List<Node> traverse(File rootFile) {
        List<Node> nodeList = new ArrayList<>();
        if (!rootFile.isDirectory()) {
            nodeList.add(new Node(rootFile.getPath()));
            return nodeList;
        }
        File[] fileArray = rootFile.listFiles();
        if (fileArray == null) {
            System.out.printf("Error occurred in the following directory %s%n", rootFile.getName());
            return nodeList;
        }
        for (File file : fileArray) {
            List<Node> list = traverse(file);
            nodeList.addAll(list);
        }
        return nodeList;
    }
}