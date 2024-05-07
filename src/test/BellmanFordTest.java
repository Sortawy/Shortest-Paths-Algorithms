package test;

import main.Algorithm;
import main.BellmanFord;
import main.Graph;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BellmanFordTest {
   
    @Test
    public void negativeCycleSmallTest() {
        Graph graph = new Graph("graph_files\\negativeCycleSmall\\negativeCycleSmall.txt");
        Algorithm algorithm = new BellmanFord();
        graph.setAlgorithm(algorithm);
        boolean isNegativeCycle=!graph.calculateShortestPaths();
        assertEquals(true, isNegativeCycle);   
    }
    
    @Test
    public void negativeCycleSmallFromSourceTest() {
       Graph graph = new Graph("graph_files\\negativeCycleSmall\\negativeCycleSmall.txt");
       Algorithm algorithm = new BellmanFord();
       graph.setAlgorithm(algorithm);
       boolean isNegativeCycle=!graph.calculateShortestPaths(0);
       assertEquals(true, isNegativeCycle);   
   }
   @Test
   public void noNegativeCycleSmallTest() {
       Graph graph = new Graph("graph_files\\noNegativeCycleSmall\\noNegativeCycleSmall.txt");
       Algorithm algorithm = new BellmanFord();
       graph.setAlgorithm(algorithm);
       boolean isNegativeCycle=!graph.calculateShortestPaths();
       assertEquals(false, isNegativeCycle);   
    }
   
   @Test
   public void noNegativeCycleSmallFromSourceTest() {
       Graph graph = new Graph("graph_files\\noNegativeCycleSmall\\\\noNegativeCycleSmall.txt");
       Algorithm algorithm = new BellmanFord();
       graph.setAlgorithm(algorithm);
       boolean isNegativeCycle=!graph.calculateShortestPaths(0);
       assertEquals(false, isNegativeCycle);   
   }
   @Test
   public void negativeCycleLargeTest() {
       Graph graph = new Graph("graph_files\\negativeCycleLarge\\negativeCycleLarge.txt");
       Algorithm algorithm = new BellmanFord();
       graph.setAlgorithm(algorithm);
       boolean isNegativeCycle=!graph.calculateShortestPaths();
       assertEquals(true, isNegativeCycle);   
   }
    @Test
    public void minimumCostPathFromSourceTest1() {
        Graph graph = new Graph("graph_files\\mediumGraph\\\\mediumGraph.txt");
        Algorithm algorithm = new BellmanFord();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(0);
        assertEquals(4, algorithm.getCost(0, 1));
        assertEquals(12,algorithm.getCost(0, 2));
        assertEquals(9, algorithm.getCost(0,3));
        assertEquals(4, algorithm.getCost(0,4));
        assertEquals(6, algorithm.getCost(0,5));
        assertEquals(5, algorithm.getCost(0,6));
        assertEquals(8, algorithm.getCost(0,7));
    }

    @Test
    public void minimumCostPathFromSourceTest2() {
        Graph graph = new Graph("graph_files\\mediumGraph\\\\mediumGraph.txt");
        Algorithm algorithm = new BellmanFord();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(1);
        assertEquals(7,algorithm.getCost(1,0));
        assertEquals(8, algorithm.getCost(1,2));
        assertEquals(5, algorithm.getCost(1,3));
        assertEquals(0, algorithm.getCost(1,4));
        assertEquals(2, algorithm.getCost(1,5));
        assertEquals(1, algorithm.getCost(1,6));
        assertEquals(4, algorithm.getCost(1,7));
    }


    @Test
    public void minimumPathNodesFromSourceTest1(){
        Graph graph = new Graph("graph_files\\mediumGraph\\\\mediumGraph.txt");
        Algorithm algorithm = new BellmanFord();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(1);
        
        List<Integer> pathToNode0 = algorithm.getPath(1, 0);
        List<Integer> expectedPathToNode0 = Arrays.asList(1, 3, 4, 5, 6, 7, 0);
        assertEquals(expectedPathToNode0, pathToNode0);

        List<Integer> pathToNode2 = algorithm.getPath(1, 2);
        List<Integer> expectedPathToNode2 = Arrays.asList(1, 2);
        assertEquals(expectedPathToNode2, pathToNode2);
    

        List<Integer> pathToNode3 = algorithm.getPath(1, 3);
        List<Integer> expectedPathToNode3 = Arrays.asList(1, 3);
        assertEquals(expectedPathToNode3, pathToNode3);

        List<Integer> pathToNode4 = algorithm.getPath(1, 4);
        List<Integer> expectedPathToNode4 = Arrays.asList(1, 3, 4);
        assertEquals(expectedPathToNode4, pathToNode4);

        List<Integer> pathToNode5 = algorithm.getPath(1, 5);
        List<Integer> expectedPathToNode5 = Arrays.asList(1, 3, 4, 5);
        assertEquals(expectedPathToNode5, pathToNode5);

        List<Integer> pathToNode6 = algorithm.getPath(1, 6);
        List<Integer> expectedPathToNode6 = Arrays.asList(1, 3, 4, 5, 6);
        assertEquals(expectedPathToNode6, pathToNode6);

        List<Integer> pathToNode7 = algorithm.getPath(1, 7);
        List<Integer> expectedPathToNode7 = Arrays.asList(1, 3, 4, 5, 6, 7);
        assertEquals(expectedPathToNode7, pathToNode7);
    }
}
