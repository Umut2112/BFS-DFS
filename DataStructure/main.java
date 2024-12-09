import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.util.*;

public class main {
    public static void main(String[] args) {
        String csvFilePath = "cities.csv"; // CSV dosyasının doğru yolunu kontrol edin

        try {
            String[][] matrix = CSVReaderUtil.readCSV(csvFilePath);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Which algorithm would you like to use?");
            System.out.println("1. BFS");
            System.out.println("2. DFS");
            System.out.print("Choose an option (1 or 2): ");
            int algorithmChoice = scanner.nextInt();
            scanner.nextLine(); // Scanner tamponunu temizle

            System.out.print("Enter the starting city: ");
            String startCity = scanner.nextLine().trim();
            System.out.print("Enter the destination city: ");
            String destinationCity = scanner.nextLine().trim();

            if (!CSVReaderUtil.doesCityExist(matrix, startCity) || !CSVReaderUtil.doesCityExist(matrix, destinationCity)) {
                System.out.println("One or both of the specified cities were not found.");
                return;
            }

            List<String> path;
            long startTime = System.currentTimeMillis();

            if (algorithmChoice == 1) {
                path = BFSUtil.findPathBFS(matrix, startCity, destinationCity);
            } else if (algorithmChoice == 2) {
                path = DFSUtil.findPathDFS(matrix, startCity, destinationCity);
            } else {
                System.out.println("Invalid choice. Exiting the program.");
                return;
            }

            long endTime = System.currentTimeMillis();
            long runtime = endTime - startTime;

            if (path != null && !path.isEmpty()) {
                int pathLength = CSVReaderUtil.calculatePathLength(matrix, path);
                System.out.println("\n--- Results ---");
                System.out.println("Path: " + path);
                System.out.println("Path Length: " + pathLength + " km");
                System.out.println("Runtime: " + runtime + " ms");
            } else {
                System.out.println("No path found using the chosen algorithm.");
            }
        } catch (IOException | CsvException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
