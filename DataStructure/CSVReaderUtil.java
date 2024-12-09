import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVReaderUtil {
    public static String[][] readCSV(String csvFilePath) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(csvFilePath));
        List<String[]> allData = reader.readAll();
        return allData.toArray(new String[0][0]);
    }

    public static boolean doesCityExist(String[][] matrix, String cityName) {
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i].equalsIgnoreCase(cityName)) {
                return true;
            }
        }
        return false;
    }

    public static Map<String, Integer> getCityIndexMap(String[][] matrix) {
        Map<String, Integer> cityIndexMap = new HashMap<>();
        for (int i = 0; i < matrix[0].length; i++) {
            cityIndexMap.put(matrix[0][i], i);
        }
        return cityIndexMap;
    }

    public static List<String> reconstructPath(int[] parent, int start, int destination, String[][] matrix) {
        List<String> path = new ArrayList<>();
        int current = destination;

        while (current != -1) {
            path.add(0, matrix[0][current]);
            current = parent[current];
        }
        return path;
    }

    public static int calculatePathLength(String[][] matrix, List<String> path) {
        int totalLength = 0;
        Map<String, Integer> cityIndexMap = getCityIndexMap(matrix);

        for (int i = 0; i < path.size() - 1; i++) {
            int fromIndex = cityIndexMap.get(path.get(i));
            int toIndex = cityIndexMap.get(path.get(i + 1));
            totalLength += Integer.parseInt(matrix[fromIndex][toIndex]);
        }
        return totalLength;
    }
}
