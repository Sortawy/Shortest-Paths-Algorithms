package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FloydWarshall implements Algorithm {
    private int[][] floydMinimumCosts;
    private List<Integer>[][] floydAllPaths;
    private int numberOfNodes;
    private int[][] costMatrix;
    private Map<Integer, List<Edge>> graph;
    private boolean containsNegativeCycle;

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
            int[][] nextNode = new int[numberOfNodes][numberOfNodes];
            return floydWarshall(this.costMatrix, nextNode);
        }
        return !containsNegativeCycle;
    }

    public void constructFloydPaths(int[][] nextNode) {
        if (this.floydAllPaths != null)
            return;
        this.floydAllPaths = new ArrayList[numberOfNodes][numberOfNodes];
        for (int u = 0; u < numberOfNodes; u++) {
            for (int v = 0; v < numberOfNodes; v++) {
                if (nextNode[u][v] == -1) {
                    this.floydAllPaths[u][v] = new ArrayList<>();
                } else {
                    int temp = u;
                    List<Integer> path = new ArrayList<>();
                    path.add(u);
                    while (temp != v) {
                        temp = nextNode[temp][v];
                        path.add(temp);
                    }
                    this.floydAllPaths[u][v] = path;
                }
            }
        }
    }

    public void setupFloydMatrices(int[][] floydCostMatrix, int[][] nextNode) {
        this.floydMinimumCosts = new int[numberOfNodes][numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                if (i == j)
                    this.floydMinimumCosts[i][j] = Math.min(floydCostMatrix[i][j],0);
                else
                    this.floydMinimumCosts[i][j] = floydCostMatrix[i][j];
                if (floydCostMatrix[i][j] != Integer.MAX_VALUE) {
                    nextNode[i][j] = j;
                } else {
                    nextNode[i][j] = -1;
                }
            }
        }
    }

    public boolean floydWarshall(int[][] floydCostMatrix, int[][] nextNode) {
        setupFloydMatrices(floydCostMatrix, nextNode);
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                if (j == i)
                    continue;
                for (int k = 0; k < numberOfNodes; k++) {
                    if (k == i)
                        continue;
                    if (this.floydMinimumCosts[j][i] != Integer.MAX_VALUE
                            && this.floydMinimumCosts[i][k] != Integer.MAX_VALUE
                            && (this.floydMinimumCosts[j][i]
                                    + this.floydMinimumCosts[i][k] < this.floydMinimumCosts[j][k])) {
                        this.floydMinimumCosts[j][k] = this.floydMinimumCosts[j][i] + this.floydMinimumCosts[i][k];
                        nextNode[j][k] = nextNode[j][i];
                    }
                }
            }
        }

        constructFloydPaths(nextNode);
        /* Now detect negative cycles */

        for (int i = 0; i < numberOfNodes; i++)
            if (this.floydMinimumCosts[i][i] < 0) {
                this.containsNegativeCycle = true;
                return false;
            }
        return true;
    }

    @Override
    public int getCost(int u, int v) {
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
        this.costMatrix = new int[numberOfNodes][numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++)
            for (int j = 0; j < numberOfNodes; j++)
                costMatrix[i][j] = Integer.MAX_VALUE;

        for (int i = 0; i < numberOfNodes; i++) {
            List<Edge> neighbours = graph.getOrDefault(i, null);
            if (neighbours == null)
                continue;
            for (Edge e : neighbours) {
                costMatrix[i][e.getDestination()] = e.getWeight();
            }
        }
    }

    @Override
    public List<Integer> getPath(int u, int v) {
        List<Integer> path = this.floydAllPaths[u][v];
        if (path.isEmpty()) {
            return null;
        }
        return path;
    }
}
