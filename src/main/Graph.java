package main;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Integer, List<Edge>> graph;
    private int numberOfNodes;
    private int numberOfEdges;

    public Graph(String filePath) {
        try {
            List<String> rows = Files.readAllLines(Paths.get(filePath));
            for (String row : rows) {
                if (row.isEmpty()) continue;
                String[] line = row.split(" ");
                if (line.length == 2 && numberOfNodes == 0) {
                    numberOfNodes = Integer.parseInt(line[0]);
                    numberOfEdges = Integer.parseInt(line[1]);
                    graph = new HashMap<>(numberOfEdges);
                }
                else if (line.length == 3) {
                    int source = Integer.parseInt(line[0]);
                    int destination = Integer.parseInt(line[1]);
                    int weight = Integer.parseInt(line[2]);
                    addEdge(source, destination, weight);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addEdge(int source, int destination, int weight) {
        if (source >= numberOfNodes || destination >= numberOfNodes) {
            return;
        }
        Edge edge = new Edge(destination, weight);
        graph.putIfAbsent(source, new ArrayList<>());
        graph.putIfAbsent(destination, new ArrayList<>());
        graph.get(source).add(edge);
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    private static class Edge {
        private int weight;
        private int destination;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public int getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }
}
