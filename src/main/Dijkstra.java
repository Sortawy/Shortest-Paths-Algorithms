package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra implements Algorithm {

    private Map<Integer, List<Edge>> graph;
    private int numberOfNodes;
    private int[][] costMatrix;
    private int[][] parent;

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
        // Initialize the priority queue and define the comparator
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> {
            if (a.getWeight() == b.getWeight())
                return a.getDestination() - b.getDestination();
            return a.getWeight() - b.getWeight();
        });
        pq.add(new Edge(source, 0));
        boolean[] marked = new boolean[numberOfNodes];
        marked[source] = true;
        costMatrix[source][source] = 0;
        parent[source][source] = source;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int u = edge.getDestination();
            marked[u] = true;
            if (graph.get(u) == null)
                continue;
            for (Edge neighbour : graph.get(u)) {
                int v = neighbour.getDestination();
                int w = neighbour.getWeight();
                if (!marked[v] && costMatrix[source][v] > costMatrix[source][u] + w) {
                    costMatrix[source][v] = costMatrix[source][u] + w;
                    parent[source][v] = u;
                    pq.add(new Edge(v, costMatrix[source][v]));
                }
            }
        }
        return false;
    }

    @Override
    public boolean calculateAllPairsShortestPaths() {
        for (int i = 0; i < numberOfNodes; i++) {
            calculateShortestPathsFromSource(i);
        }
        return false;
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
        init();
    }

    public void setGraph(Map<Integer, List<Edge>> graph) {
        this.graph = graph;
    }
}
