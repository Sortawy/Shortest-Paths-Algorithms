package main;

import java.util.List;
import java.util.Map;

public class BellmanFord implements Algorithm {

    @Override
    public boolean calculateShortestPathsFromSource(int source) {
        return false;
    }

    @Override
    public boolean calculateAllPairsShortestPaths() {
        return false;
    }

    @Override
    public List<Integer> getPath(int u, int v) {
        return null;
    }

    @Override
    public int getCost(int u, int v) {
        return 0;
    }

    @Override
    public void setNumberOfNodes(int numberOfNodes) {

    }

    @Override
    public void setGraph(Map<Integer, List<Edge>> graph) {

    }
}
