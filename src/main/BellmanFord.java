package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BellmanFord implements Algorithm {
   
    private Map<Integer, List<Edge>> graph;
    private int numberOfNodes;
    private int[][] costMatrix;
    private int[][] parent;
    private boolean containsNegativeCycle;


    private void init() {
        this.costMatrix = new int[numberOfNodes][numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                if (i == j)
                    this.costMatrix[i][j] = 0;
                else
                    this.costMatrix[i][j] = Integer.MAX_VALUE;
            }
        }
        this.parent = new int[numberOfNodes][numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                this.parent[i][j] = -1;
            }
        }
    }

    @Override
    public boolean calculateShortestPathsFromSource(int source) {
        for (int i = 0; i < numberOfNodes; i++) {
            costMatrix[source][i] = Integer.MAX_VALUE;
            parent[source][i] = -1;
        }
        costMatrix[source][source] = 0;
        parent[source][source] = source;

        for (int i = 0; i < numberOfNodes - 1; i++) {
            for (int src = 0; src < numberOfNodes; src++) {
                if(!graph.containsKey(src))
                    continue;
                for (Edge edge : graph.get(src)) {
                    int dest = edge.getDestination();
                    int weight = edge.getWeight();
                    if (costMatrix[source][src] != Integer.MAX_VALUE && costMatrix[source][src] + weight < costMatrix[source][dest]) {
                        costMatrix[source][dest] = costMatrix[source][src] + weight;
                        parent[source][dest] = src;
                    }
                }
            }
        }
        for (int src = 0; src < numberOfNodes; src++) {
            if(!graph.containsKey(src))
                    continue;
            for (Edge edge : graph.get(src)) {
                int dest = edge.getDestination();
                int weight = edge.getWeight();
                if (costMatrix[source][src] != Integer.MAX_VALUE && costMatrix[source][src] + weight < costMatrix[source][dest]) {
                    containsNegativeCycle = true;
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean calculateAllPairsShortestPaths() {
        for (int i = 0; i < numberOfNodes; i++) {
            calculateShortestPathsFromSource(i);
        }
        // return false;
        return !containsNegativeCycle;
    }
    
    @Override
    public List<Integer> getPath(int u, int v) {

        if (parent[u][v] == -1) {
            return null;
        }
        List<Integer> path = new ArrayList<>();
        int temp = v;
        while (temp != u) {
            path.add(temp);
            temp = parent[u][temp];
        }
        path.add(u);
        Collections.reverse(path);
        return path;

    }

    @Override
    public int getCost(int u, int v) {
        return costMatrix[u][v];
    }

    @Override
    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        this.containsNegativeCycle = false;
        init();
    }

    @Override
    public void setGraph(Map<Integer, List<Edge>> graph) {
        this.graph = graph;
    }
    
    public boolean containsNegativeCycle(){
        // if (containsNegativeCycle == true)
        //     return true;
        boolean visited[] = new boolean[numberOfNodes];
        Arrays.fill(visited, false);
       
        
        for (int src = 0; src < numberOfNodes; src++){
            if (!visited[src]){
                if (!calculateShortestPathsFromSource(src)){
                    return true;
                }
                for (int j = 0; j < numberOfNodes; j++)
                        if ( costMatrix[src][j] != Integer.MAX_VALUE)
                            visited[j] = true;
            }
        
        }
        return false;
    }
    public static void main(String[] args) {
        Graph graph = new Graph("graph_files\\Dijkstra2.txt");
        Algorithm algorithm = new BellmanFord();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(1);
        // System.out.println(algorithm.calculateShortestPaths(1));
    }
}
