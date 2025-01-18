import java.util.*;

public class ShortestPathDFS {
    static class Edge {
        int destination;
        int weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static List<Integer> shortestPathDFS(int[][] graph, int source, int destination) {
        int numNodes = graph.length;
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> distance = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();

        stack.push(source);
        visited.add(source);
        distance.put(source, 0);
        parent.put(source, -1);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            for (int nextNode = 0; nextNode < numNodes; nextNode++) {
                int edgeWeight = graph[current][nextNode];

                if (edgeWeight != 99999) {
                    int newDistance = distance.get(current) + edgeWeight;

                    if (!visited.contains(nextNode) || newDistance < distance.get(nextNode)) {
                        distance.put(nextNode, newDistance);
                        parent.put(nextNode, current);

                        if (!visited.contains(nextNode)) {
                            stack.push(nextNode);
                            visited.add(nextNode);
                        }
                    }
                }
            }
        }

        if (!parent.containsKey(destination)) {
            System.out.println("No path from " + source + " to " + destination);
            return new ArrayList<>();
        }

        List<Integer> path = new ArrayList<>();
        int current = destination;
        while (parent.get(current) != -1) {
            path.add(current);
            current = parent.get(current);
        }

        path.add(source);
        Collections.reverse(path);

        System.out.println("Shortest Distance is : " + distance.get(destination));

        return path;
    }
    public static Integer getDistance(int[][] graph, int source, int destination) {
        int numNodes = graph.length;
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> distance = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();

        stack.push(source);
        visited.add(source);
        distance.put(source, 0);
        parent.put(source, -1);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            for (int nextNode = 0; nextNode < numNodes; nextNode++) {
                int edgeWeight = graph[current][nextNode];

                if (edgeWeight != 99999) {
                    int newDistance = distance.get(current) + edgeWeight;

                    if (!visited.contains(nextNode) || newDistance < distance.get(nextNode)) {
                        distance.put(nextNode, newDistance);
                        parent.put(nextNode, current);

                        if (!visited.contains(nextNode)) {
                            stack.push(nextNode);
                            visited.add(nextNode);
                        }
                    }
                }
            }
        }

        if (!parent.containsKey(destination)) {
            System.out.println("No path from " + source + " to " + destination);
           
        }

        List<Integer> path = new ArrayList<>();
        int current = destination;
        while (parent.get(current) != -1) {
            path.add(current);
            current = parent.get(current);
        }

        path.add(source);
        Collections.reverse(path);

        System.out.println("Shortest Distance is : " + distance.get(destination));

        return distance.get(destination);
    }

    public static void main(String[] args) {
        
    }
}
