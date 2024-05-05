package main;

public interface Algorithm {
    boolean calculateShortestPathsFromSource(int source);
    boolean calculateAllPairsShortestPaths();
    void printPath(int u, int v);
    void printCost(int u, int v);
    void setNumberOfNodes(int numberOfNodes);
    void setCostMatrix(int [][] costMatrix);
}
