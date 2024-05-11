package test;

import main.Algorithm;
import main.Edge;
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
        long startTime=System.currentTimeMillis();
        if (allPairs)
            algorithm.calculateAllPairsShortestPaths();
        else
            algorithm.calculateShortestPathsFromSource(0);
        long endTime=System.currentTimeMillis();
        return endTime-startTime;
    }
}