import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileManager fm = new FileManager("Tests");
        List<Node> sortedNodes = fm.getSortedNodes();
        fm.printNodes(sortedNodes);
        fm.ConcatenateFiles(sortedNodes);
    }
}