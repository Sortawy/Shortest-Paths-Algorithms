package main;

import java.util.List;
import java.util.Map;

public interface Algorithm {
    boolean calculateShortestPathsFromSource(int source);

    boolean calculateAllPairsShortestPaths();

    List<Integer> getPath(int u, int v);

    int getCost(int u, int v);

    void setNumberOfNodes(int numberOfNodes);

    void setGraph(Map<Integer, List<Edge>> graph);
  
     
}
