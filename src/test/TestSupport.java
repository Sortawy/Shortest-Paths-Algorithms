package test;

import main.Algorithm;
import main.Edge;
import main.Graph;
import main.MeanTime;

import java.util.List;
import java.util.Map;

public class TestSupport {
    public static long testRandomGraphOfSize(int size, Algorithm algorithm, boolean sparse, boolean allPairs){
        Map<Integer, List<Edge>> graph;
        if (sparse)
            graph=MeanTime.generateRandomGraph(size,size);
        else
            graph=MeanTime.generateRandomGraph(size,size*size);
        Graph g = new Graph(graph);
        g.setAlgorithm(algorithm);
        long startTime=System.currentTimeMillis();
        if (allPairs)
            g.calculateShortestPaths();
        else
            g.calculateShortestPaths(0);
        long endTime=System.currentTimeMillis();
        return endTime-startTime;
    }
}