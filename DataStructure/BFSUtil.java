import java.util.*;

public class BFSUtil {
    public static List<String> findPathBFS(String[][] matrix, String startCity, String destinationCity) {
        System.out.println("Starting BFS from: " + startCity + " to " + destinationCity);
        Queue<List<String>> queue = new LinkedList<>();
        Map<String, Boolean> visited = new HashMap<>();
        List<String> startPath = new ArrayList<>();
        startPath.add(startCity);
        queue.add(startPath);
        visited.put(startCity, true);

        while (!queue.isEmpty()) {
            List<String> currentPath = queue.poll();
            String currentCity = currentPath.get(currentPath.size() - 1);

            if (currentCity.equalsIgnoreCase(destinationCity)) {
                System.out.println("Path found using BFS: " + currentPath);
                return currentPath;
            }

            int currentIndex = Arrays.asList(matrix[0]).indexOf(currentCity);

            // Hata kontrolü: currentCity'nin matrix[0] içinde bulunup bulunmadığını kontrol et
            if (currentIndex == -1) {
                System.out.println("City not found in matrix: " + currentCity);
                return null;
            }

            for (int i = 1; i < matrix.length; i++) {
                if (!visited.containsKey(matrix[i][0]) && !matrix[i][currentIndex].equals("99999")) {
                    List<String> newPath = new ArrayList<>(currentPath);
                    newPath.add(matrix[i][0]);
                    queue.add(newPath);
                    visited.put(matrix[i][0], true);
                }
            }
        }
        return null; // Eğer yol bulunamazsa
    }
}
