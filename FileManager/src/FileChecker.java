import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Вспомогательный класс, отвечающий за проверку файлов на наличие require и дальнейшей работе с ними
 */
public class FileChecker {
    /**
     * проверяет файлы на наличие require
     * @param file файл, который мы проверяем на зависимость от других файлов
     * @param directory директория
     * @return список файлов, от которых зависит входной файл
     */
    public static List<Node> ScanFiles(Node file, String directory) {
        List<Node> nodeList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file.toString()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("require") && new File(refactorString(line, directory)).isFile()) {
                    nodeList.add(new Node(refactorString(line, directory)));
                }
            }
        } catch (IOException e) {
            System.out.println("Что-то пошло не так");
            e.printStackTrace();
        }
        return nodeList;
    }
    /**
     * убирает require, добавляет относительный путь
     * @param line сторка с require из файла
     * @param directory директория (для создания относительного пути)
     * @return измененная строка
     */
    private static String refactorString(String line, String directory) {
        line = line.substring(9);
        return directory + File.separator + line.substring(0,line.length()- 1);
    }
}

