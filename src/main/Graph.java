package main;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Graph {
    private Map<Integer, List<Edge>> graph;

    private int numberOfNodes;
    private int numberOfEdges;
    private int[][] costMatrix;
    private Algorithm algorithm;

    public Graph(String filePath) {
        try {
            List<String> rows = Files.readAllLines(Paths.get(filePath));
            for (String row : rows) {
                if (row.isEmpty())
                    continue;
                String[] line = row.split(" ");
                if (line.length == 2 && numberOfNodes == 0) {
                    numberOfNodes = Integer.parseInt(line[0]);
                    numberOfEdges = Integer.parseInt(line[1]);
                    graph = new HashMap<>(numberOfNodes);
                    costMatrix = new int[numberOfNodes][numberOfNodes];
                    for (int i = 0; i < numberOfNodes; i++)
                        for (int j = 0; j < numberOfNodes; j++)
                            costMatrix[i][j] = Integer.MAX_VALUE;
                } else if (line.length == 3) {
                    int source = Integer.parseInt(line[0]);
                    int destination = Integer.parseInt(line[1]);
                    int weight = Integer.parseInt(line[2]);
                    addEdge(source, destination, weight);
                    costMatrix[source][destination] = weight;
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

    /**
     * Function takes the source node and applies algorithm to get all pairs
     * shortest paths
     * 
     * @return a boolean : True indicates negative cycles, BUT this return value is
     *         redundant
     *         for Dijkstra algorithm and shouldn't be used.
     */
    public boolean calculateShortestPaths() {
        return algorithm.calculateAllPairsShortestPaths();
    }

    /**
     * Function takes the source node and applies algorithm to get shortest paths
     * from source node
     * 
     * @param source : The source node of the path
     * @return a boolean : True indicates negative cycles, BUT this return value is
     *         redundant
     *         for Dijkstra algorithm and shouldn't be used.
     */
    public boolean calculateShortestPaths(int source) {
        return algorithm.calculateShortestPathsFromSource(source);
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
        this.algorithm.setCostMatrix(this.costMatrix);
        this.algorithm.setNumberOfNodes(this.numberOfNodes);
        this.algorithm.setGraph(this.graph);
    }

}
