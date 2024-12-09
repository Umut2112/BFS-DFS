import java.util.*;

public class DFSUtil {
    public static List<String> findPathDFS(String[][] matrix, String startCity, String destinationCity) {
        System.out.println("Starting DFS from: " + startCity + " to " + destinationCity);
        List<String> path = new ArrayList<>();
        Map<String, Boolean> visited = new HashMap<>();
        if (dfs(matrix, startCity, destinationCity, path, visited)) {
            System.out.println("Path found using DFS: " + path);
            return path;
        }
        return null; // Eğer yol bulunamazsa
    }

    private static boolean dfs(String[][] matrix, String currentCity, String destinationCity, List<String> path, Map<String, Boolean> visited) {
        path.add(currentCity);
        visited.put(currentCity, true);

        if (currentCity.equalsIgnoreCase(destinationCity)) {
            return true;
        }

        int currentIndex = Arrays.asList(matrix[0]).indexOf(currentCity);
        for (int i = 1; i < matrix.length; i++) {
            if (!visited.containsKey(matrix[i][0]) && !matrix[i][currentIndex].equals("99999")) {
                if (dfs(matrix, matrix[i][0], destinationCity, path, visited)) {
                    return true;
                }
            }
        }
        path.remove(path.size() - 1); // Geri döngü için şehri çıkar
        return false;
    }
}
