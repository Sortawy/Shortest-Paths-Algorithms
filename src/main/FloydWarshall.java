package main;

import java.util.*;

public class FloydWarshall implements Algorithm {
    private long[][] floydMinimumCosts;
    private List<Integer>[][] floydAllPaths;
    private int numberOfNodes;
    private long[][] costMatrix;
    private Map<Integer, List<Edge>> graph;
    private boolean containsNegativeCycle;
    private int [][] prev; // to build the path
    @Override
    public boolean calculateShortestPathsFromSource(int source) {
        return calculateAllPairsShortestPaths();
    }

    @Override
    public boolean calculateAllPairsShortestPaths() {
        if (this.costMatrix == null) {
            buildCostMatrix();
        }
        if (this.floydMinimumCosts == null) {
            this.prev = new int[numberOfNodes][numberOfNodes];
            return floydWarshall(this.costMatrix);
        }
        return !containsNegativeCycle;
    }

    public void setupFloydMatrices(long[][] floydCostMatrix) {
        this.floydMinimumCosts = new long[numberOfNodes][numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                if (i == j) {
                    prev[i][i]=i;
                    if(floydCostMatrix[i][j]<0)this.containsNegativeCycle=true;
                    this.floydMinimumCosts[i][j] = 0;
                }
                else
                    this.floydMinimumCosts[i][j] = floydCostMatrix[i][j];
                if (floydCostMatrix[i][j] != Long.MAX_VALUE) {
                    prev[i][j] = i;
                } else {
                    prev[i][j] = -1;
                }
            }
        }
    }

    public boolean floydWarshall(long[][] floydCostMatrix) {
        setupFloydMatrices(floydCostMatrix);
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                for (int k = 0; k < numberOfNodes; k++) {
                    if (this.floydMinimumCosts[j][i] != Long.MAX_VALUE
                            && this.floydMinimumCosts[i][k] != Long.MAX_VALUE
                            && (this.floydMinimumCosts[j][i]
                                    + this.floydMinimumCosts[i][k] < this.floydMinimumCosts[j][k])) {
                        this.floydMinimumCosts[j][k] = this.floydMinimumCosts[j][i] + this.floydMinimumCosts[i][k];
                        prev[j][k] = prev[i][k];
                    }
                }
            }
        }

        /* Now detect negative cycles */
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                for (int k = 0; k < numberOfNodes; k++) {
                    if (this.floydMinimumCosts[j][i] != Long.MAX_VALUE
                            && this.floydMinimumCosts[i][k] != Long.MAX_VALUE
                            && (this.floydMinimumCosts[j][i]
                            + this.floydMinimumCosts[i][k] < this.floydMinimumCosts[j][k])) {
                        this.floydMinimumCosts[j][k] = Long.MIN_VALUE;
                        this.containsNegativeCycle=true;
                    }
                }
            }
        }
        /* If break after finding negative cycles
        for (int i = 0; i < numberOfNodes; i++)
            if (this.floydMinimumCosts[i][i] < 0) {
                this.containsNegativeCycle = true;
                return false;
            }
        */
        return !this.containsNegativeCycle;
    }

    @Override
    public long getCost(int u, int v) {
        return this.floydMinimumCosts[u][v];
    }

    @Override
    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }

    @Override
    public void setGraph(Map<Integer, List<Edge>> graph) {
        this.graph = graph;
    }

    public void buildCostMatrix() {
        this.costMatrix = new long[numberOfNodes][numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++)
            for (int j = 0; j < numberOfNodes; j++)
                costMatrix[i][j] = Long.MAX_VALUE;

        for (int i = 0; i < numberOfNodes; i++) {
            List<Edge> neighbours = graph.getOrDefault(i, null);
            if (neighbours == null)
                continue;
            for (Edge e : neighbours) {
                costMatrix[i][e.getDestination()] = Math.min(e.getWeight(),costMatrix[i][e.getDestination()]);
            }
        }
    }

    @Override
    public List<Integer> getPath(int u, int v) {
        List<Integer> path = new ArrayList<>();
        if (prev[u][v]==-1){
            if (u==v)
                return Arrays.asList(u);
            return null;
        }
        path.add(v);
        while (v!=u){
            v=prev[u][v];
            path.add(v);
        }
        Collections.reverse(path);
        return path;
    }
}
