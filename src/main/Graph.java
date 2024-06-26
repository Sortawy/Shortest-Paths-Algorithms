package main;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Graph {
    private Map<Integer, List<Edge>> graph;
    private int numberOfNodes;
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
                    graph = new HashMap<>(numberOfNodes);
                } else if (line.length == 3) {
                    int source = Integer.parseInt(line[0]);
                    int destination = Integer.parseInt(line[1]);
                    long weight = Long.parseLong(line[2]);
                    addEdge(source, destination, weight);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Graph(Map<Integer,List<Edge>>g){
        this.graph=g;
        this.numberOfNodes=g.size();
    }

    private void addEdge(int source, int destination, long weight) {
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
        this.algorithm.setNumberOfNodes(this.numberOfNodes);
        this.algorithm.setGraph(this.graph);
    }
}
