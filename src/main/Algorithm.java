package main;

import java.util.List;
import java.util.Map;

public interface Algorithm {
    boolean calculateShortestPathsFromSource(int source);
    boolean calculateAllPairsShortestPaths();
    void printPath(int u, int v);
    void printCost(int u, int v);
    void setNumberOfNodes(int numberOfNodes);
    void setGraph(Map<Integer, List<Edge>> graph);
}
