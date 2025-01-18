import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class GraphReader {
    public static List<String> pathBFS(String inputSource, String inputDestination){
        
        Map<String, Integer> cityNameToIndex = new HashMap<>();
        Map<Integer, String> cityIndexToName = new HashMap<>();
        int[][] graph = readGraphFromCSV( cityNameToIndex, cityIndexToName);
        List<String> nullstring = null;

        
        if (graph != null) {
            Integer sourceIndex = cityNameToIndex.get(inputSource);
            Integer destinationIndex = cityNameToIndex.get(inputDestination);

            if (sourceIndex != null && destinationIndex != null) {
                int source = sourceIndex;
                int destination = destinationIndex;

                List<Integer> shortestPath = ShortestPathBFS.shortestPathBFS(graph, source, destination);

                if (!shortestPath.isEmpty()) {
                    List<String> pathWithCityNames = new ArrayList<>();
                    for (int index : shortestPath) {
                        pathWithCityNames.add(cityIndexToName.get(index));
                    }

                    System.out.println("Shortest Path from " + cityIndexToName.get(source) + " to " + cityIndexToName.get(destination) + ": " + pathWithCityNames);
                    return pathWithCityNames;
                } else {
                    System.out.println("No path from " + cityIndexToName.get(source) + " to " + cityIndexToName.get(destination));
                    return nullstring;
                }
            } else {
                System.out.println("City names not found in the map.");
                return nullstring;
            }
        }
        return nullstring;
    }

    public static List<String> pathDFS(String inputSource, String inputDestination){
  
        Map<String, Integer> cityNameToIndex = new HashMap<>();
        Map<Integer, String> cityIndexToName = new HashMap<>();
        int[][] graph = readGraphFromCSV( cityNameToIndex, cityIndexToName);
        List<String> nullstring = null;

        
        if (graph != null) {
            Integer sourceIndex = cityNameToIndex.get(inputSource);
            Integer destinationIndex = cityNameToIndex.get(inputDestination);

            if (sourceIndex != null && destinationIndex != null) {
                int source = sourceIndex;
                int destination = destinationIndex;

                List<Integer> shortestPathDFS = ShortestPathDFS.shortestPathDFS(graph, source, destination);
                if (!shortestPathDFS.isEmpty()) {
                    List<String> pathWithCityNamesDFS = new ArrayList<>();
                    for (int index : shortestPathDFS) {
                        pathWithCityNamesDFS.add(cityIndexToName.get(index));
                    }

                    System.out.println("Shortest Path (DFS) from " + cityIndexToName.get(source) + " to " + cityIndexToName.get(destination) + ": " + pathWithCityNamesDFS);
                    return pathWithCityNamesDFS;
                }else {
                    System.out.println("No path (DFS) from " + cityIndexToName.get(source) + " to " + cityIndexToName.get(destination));
                    return nullstring;
                }
            } 
        }else {
            System.out.println("City names not found in the map.");
        }
        return nullstring;
                
    }

    public static Integer getDistanceBFS(String inputSource, String inputDestination){
        Map<String, Integer> cityNameToIndex = new HashMap<>();
        Map<Integer, String> cityIndexToName = new HashMap<>();
        int[][] graph = readGraphFromCSV( cityNameToIndex, cityIndexToName);

        Integer sourceIndex = cityNameToIndex.get(inputSource);
        Integer destinationIndex = cityNameToIndex.get(inputDestination);

        int source = sourceIndex;
        int destination = destinationIndex;

        return ShortestPathBFS.getDistance(graph, source, destination);
    }
    public static Integer getDistanceDFS(String inputSource, String inputDestination){
        Map<String, Integer> cityNameToIndex = new HashMap<>();
        Map<Integer, String> cityIndexToName = new HashMap<>();
        int[][] graph = readGraphFromCSV( cityNameToIndex, cityIndexToName);

        Integer sourceIndex = cityNameToIndex.get(inputSource);
        Integer destinationIndex = cityNameToIndex.get(inputDestination);

        int source = sourceIndex;
        int destination = destinationIndex;

        return ShortestPathDFS.getDistance(graph, source, destination);
    }

    
    public static int[][] readGraphFromCSV(Map<String, Integer> cityNameToIndex, Map<Integer, String> cityIndexToName) {
        try (InputStream inputStream = GraphReader.class.getClassLoader().getResourceAsStream("Turkishcities.csv");
             Scanner scanner = new Scanner(inputStream)) {
            String[] headers = scanner.nextLine().split(",");
            int numNodes = headers.length - 1;
            int[][] graph = new int[numNodes][numNodes];

            for (int i = 0; i < numNodes; i++) {
                cityNameToIndex.put(headers[i + 1], i);
                cityIndexToName.put(i, headers[i + 1]);
            }

            int rowIndex = 0;
            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(",");
                for (int colIndex = 1; colIndex < values.length; colIndex++) {
                    graph[rowIndex][colIndex - 1] = Integer.parseInt(values[colIndex]);
                }
                rowIndex++;
            }

            return graph;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


