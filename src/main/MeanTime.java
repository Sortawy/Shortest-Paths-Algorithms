package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeanTime {

    /**
     * Generate a random graph with n vertices and m edges.
     * The weights of the edges are random integers between -1000 and 1000.
     * 
     * @param n number of vertices
     * @param m number of edges
     * @return a random graph
     */
    
    public static Map<Integer, List<Edge>> generateRandomGraph(int n, int m) {
        Map<Integer, List<Edge>> graph = new HashMap<>(n);
        for (int i = 0; i < m; i++) {
            int source = (int) (Math.random() * n);
            int destination = (int) (Math.random() * n);
            long weight = (long) (Math.random() * 1000) + 1;
            Edge edge = new Edge(destination, weight);
            graph.putIfAbsent(source, new ArrayList<>());
            graph.putIfAbsent(destination, new ArrayList<>());
            graph.get(source).add(edge);
        }
        return graph;
    }

    
    /**
     * function to define kind of algrithm to use
     * 
     * @param algorithmName : String
     * 
     * return new instance of the algorithm
     */
    
    public static Algorithm getAlgorithm(String algorithmName) {
        switch (algorithmName) {
            case "Dijkstra":
            return new Dijkstra();
            case "BellmanFord":
            return new BellmanFord();
            case "FloydWarshall":
            return new FloydWarshall();
            default:
            throw new IllegalArgumentException("Invalid algorithm");
        }
    }
        
    /**
     * Calculate the mean time of running a selected algorithm on a random graph.
     * 
     * @param n number of vertices
     * @param m number of edges
     * @param algorithmName the selected algorithm
     * 
     * @return the mean time to calculate shortest Path for one pair and for all pairs
     */
        
    public static double[] calculateMeanTime(int n, int m, String algorithmName) {
        int numberOfTrials = 30;
        double totalTimeForPair = 0, totalTimeForAllPairs = 0;
        for (int i = 0; i < numberOfTrials; i++) {
            Map<Integer, List<Edge>> graph = generateRandomGraph(n, m);
            System.out.println("OK! "+ i);
            for(int j = 0; j < 2; j++){
                Algorithm algorithm = getAlgorithm(algorithmName);
                algorithm.setGraph(graph);
                algorithm.setNumberOfNodes(n);
                double startTime = System.nanoTime();
                if(j == 0) {
                    algorithm.calculateAllPairsShortestPaths();
                    double endTime = System.nanoTime();
                    totalTimeForAllPairs += endTime - startTime;
                } else {
                    algorithm.calculateShortestPathsFromSource(graph.keySet().iterator().next());  
                    double endTime = System.nanoTime();
                    totalTimeForPair += endTime - startTime;
                }
            }
        }
        return new double[] {totalTimeForAllPairs/numberOfTrials/1e6, totalTimeForPair/numberOfTrials/1e6};
    }

    /**
     *  Calculate the mean time of different sizes for each algorithm
     *  
     *  return Map<String, List<Double>> : A map that contains the name of the algorithm as a key and a list of mean times as a value
     */
    public static Map<String, List<double[]>> calculateMeanTimeForDifferentSizes() {
        Map<String, List<double[]>> meanTimes = new HashMap<>();
        String[] algorithmNames = new String[] {"BellmanFord"};
        int[] sizes = {10, 30, 50, 100, 500, 1000};
        for (int n : sizes) {
            for (int m : new int[] {n, n*n}) {
                    for (String algorithmName : algorithmNames) {
                        meanTimes.putIfAbsent(algorithmName, new ArrayList<>());
                        meanTimes.get(algorithmName).add(calculateMeanTime(n, m, algorithmName));
                    }
            }
        }
        return meanTimes;
    }

    public static void main(String[] args) {
        Map<String, List<double[]>> meanTimes = calculateMeanTimeForDifferentSizes();
        int[] sizes = {10, 30, 50, 100, 500, 1000};
        int sparse = 0;
        for (Map.Entry<String, List<double[]>> entry : meanTimes.entrySet()) {
            sparse = 0;
            System.out.println(entry.getKey() + ": ");
            for(double[] times : entry.getValue()){
                if(sparse%2 == 0){
                    System.out.print("sparse graph: " + sizes[sparse/2] + " vertices, " + sizes[sparse/2] + " edges: ");
                } else {
                    System.out.print("dense graph: " + sizes[sparse/2] + " vertices, " + sizes[sparse/2] * sizes[sparse/2] + " edges: ");
                }
                System.out.println("all pairs: " + String.format("%.2f", times[0]) + " ms" + " --- " + "one pair: " + String.format("%.2f", times[1]) + " ms");
                sparse++;
            }
        }
    }

}