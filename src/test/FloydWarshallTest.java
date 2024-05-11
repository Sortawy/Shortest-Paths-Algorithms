package test;

import main.Algorithm;
import main.FloydWarshall;
import main.Graph;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static main.Main.graph;
import static org.junit.Assert.*;

public class FloydWarshallTest {
    @Test
    public void negativeCycleSmallTest() {
        Graph graph = new Graph("graph_files\\negativeCycleSmall\\negativeCycleSmall.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        boolean isNegativeCycle=!graph.calculateShortestPaths();
        assertEquals(true, isNegativeCycle);
    }

    @Test
    public void negativeCycleSmallFromSourceTest() {
        Graph graph = new Graph("graph_files\\negativeCycleSmall\\negativeCycleSmall.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        boolean isNegativeCycle=!graph.calculateShortestPaths(0);
        assertEquals(true, isNegativeCycle);
    }
    @Test
    public void noNegativeCycleSmallTest() {
        Graph graph = new Graph("graph_files\\noNegativeCycleSmall\\noNegativeCycleSmall.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        boolean isNegativeCycle=!graph.calculateShortestPaths();
        assertEquals(false, isNegativeCycle);
    }

    @Test
    public void noNegativeCycleSmallFromSourceTest() {
        Graph graph = new Graph("graph_files\\noNegativeCycleSmall\\\\noNegativeCycleSmall.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        boolean isNegativeCycle=!graph.calculateShortestPaths(0);
        assertEquals(false, isNegativeCycle);
    }
    @Test
    public void minimumCostPathFromSourceTest1() {
        Graph graph = new Graph("graph_files\\mediumGraph\\\\mediumGraph.txt");
        Algorithm algorithm = new FloydWarshall();
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
        Algorithm algorithm = new FloydWarshall();
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
        Algorithm algorithm = new FloydWarshall();
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
    @Test
    public void simpleTest() {
        Graph graph = new Graph("graph_files\\Dijkstra1.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(1);
        List<Integer> path = algorithm.getPath(1, 0);
        List<Integer> expectedPath = Arrays.asList(1, 4, 3, 0);
        assertEquals(expectedPath, path);
        assertEquals(5, algorithm.getCost(1, 0));
    }

    @Test
    public void NoPathTest() {
        Graph graph = new Graph("graph_files\\Dijkstra1.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(4);
        List<Integer> path = algorithm.getPath(4, 2);
        assertNull(path);
        assertEquals(Long.MAX_VALUE, algorithm.getCost(4, 2));
    }

    @Test
    public void NegativeWeightTest() {
        Graph graph = new Graph("graph_files\\graph2.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(0);
        List<Integer> path = algorithm.getPath(0, 2);
        List<Integer> expectedPath = Arrays.asList(0, 2);
        assertEquals(expectedPath, path);
        assertEquals(7, algorithm.getCost(0, 2));
    }


    @Test
    public void LargetTest() {
        Graph graph = new Graph("graph_files\\Dijkstra2.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(1);
        List<Integer> path = algorithm.getPath(1, 0);
        List<Integer> expectedPath = Arrays.asList(1, 9, 5, 8, 0);
        assertEquals(expectedPath, path);
        assertEquals(524, algorithm.getCost(1, 0));
    }

    @Test
    public void sameNodeTest() {
        Graph graph = new Graph("graph_files\\Dijkstra1.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(1);
        List<Integer> path = algorithm.getPath(1, 1);
        List<Integer> expectedPath = List.of(1);
        assertEquals(expectedPath, path);
        assertEquals(0, algorithm.getCost(1, 1));
    }

    @Test
    public void singleNodeTest() {
        Graph graph = new Graph("graph_files\\SingleNode.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(0);
        List<Integer> path = algorithm.getPath(0, 0);
        List<Integer> expectedPath = List.of(0);
        assertEquals(expectedPath, path);
        assertEquals(0, algorithm.getCost(0, 0));
    }

    @Test
    public void disconnectedGraphTest() {
        Graph graph = new Graph("graph_files\\Disconnected.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(95);
        List<Integer> path = algorithm.getPath(95, 0);
        assertNull(path);
        assertEquals(Long.MAX_VALUE, algorithm.getCost(95, 0));
    }

    @Test
    public void DuplicatesGraphTest() {
        Graph graph = new Graph("graph_files\\Duplicates.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths();
        List<Integer> path = algorithm.getPath(2, 5);
        List<Integer> expectedPath = Arrays.asList(2, 3, 4, 5);
        assertEquals(expectedPath, path);
        assertEquals(-2, algorithm.getCost(2, 5));
    }
    @Test
    public void simpleCaseCheckNegativeSelfLoop(){
        Graph graph = new Graph("graph_files\\negative-cycle-graphs\\neg1-selfloop.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        assertFalse(graph.calculateShortestPaths(1));
    }
    @Test
    public void simpleCasePositiveSelfLoop(){
        Graph graph = new Graph("graph_files\\positive-selfloop.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        assertTrue(graph.calculateShortestPaths(1));
        assertEquals(algorithm.getPath(1,1),Arrays.asList(1));
    }
    @Test
    public void floydTestCaseOne(){
        Graph graph = new Graph("graph_files\\floyd1.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        List<List<Integer>>expected_costs = Arrays.asList(Arrays.asList(0,3,8,2,-4),
                Arrays.asList(3,0,11,1,-1),
                Arrays.asList(-3,0,0,-5,-7),
                Arrays.asList(2,5,10,0,-2),
                Arrays.asList(8,11,16,6,0));
        assertTrue(graph.calculateShortestPaths());
        for (int i = 0; i< graph.getNumberOfNodes();i++){
            for (int j =0;j <graph.getNumberOfNodes();j++){
                assertEquals((int)expected_costs.get(i).get(j),(int)algorithm.getCost(i,j));
            }
        }
    }
    @Test
    public void testCaseWithSmallSize(){
        Graph graph = new Graph("graph_files\\graph3.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        assertTrue(graph.calculateShortestPaths(1));
        assertEquals(algorithm.getCost(1,9),500);
        assertEquals(algorithm.getCost(1,10),Long.MAX_VALUE);
    }
    @Test
    public void checkNegativeCycleTestTwo(){
        Graph graph = new Graph("graph_files\\negative-cycle-graphs\\neg2.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        assertFalse(graph.calculateShortestPaths());
    }
    @Test
    public void checkNegativeCycleTestThree(){
        Graph graph = new Graph("graph_files\\negative-cycle-graphs\\neg3.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        assertFalse(graph.calculateShortestPaths());
    }

    @Test
    public void testTimeForSize100SparseAllPairs(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(100, new FloydWarshall(), true, true);
        assertTrue(elapsedTime<=100); // less than or equal 100ms
    }
    @Test
    public void testTimeForSize100SparseFromSource(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(100, new FloydWarshall(), true, false);
        assertTrue(elapsedTime<=1000); // less than or equal 1000ms
    }
    @Test
    public void testTimeForSize100DenseAllPairs(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(100, new FloydWarshall(), false, true);
        assertTrue(elapsedTime<=100); // less than or equal 100ms
    }
    @Test
    public void testTimeForSize100DenseFromSource(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(100, new FloydWarshall(), false, false);
        assertTrue(elapsedTime<=100); // less than or equal 100ms
    }
    @Test
    public void testTimeForSize500SparseAllPairs(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(500, new FloydWarshall(), true, true);
        assertTrue(elapsedTime<=5000); // less than or equal 5000ms
    }
    @Test
    public void testTimeForSize500SparseFromSource(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(500, new FloydWarshall(), true, false);
        assertTrue(elapsedTime<=5000); // less than or equal 5000ms
    }
    @Test
    public void testTimeForSize500DenseAllPairs(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(500, new FloydWarshall(), false, true);
        assertTrue(elapsedTime<=10000); // less than or equal 10000ms
    }
    @Test
    public void testTimeForSize500DenseFromSource(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(500, new FloydWarshall(), false, false);
        assertTrue(elapsedTime<=10000); // less than or equal 10000ms
    }

    @Test
    public void testTimeForSize1000SparseAllPairs(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(1000, new FloydWarshall(), true, true);
        assertTrue(elapsedTime<=20000); // less than or equal 20000ms
    }
    @Test
    public void testTimeForSize1000SparseFromSource(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(1000, new FloydWarshall(), true, false);
        assertTrue(elapsedTime<=30000); // less than or equal 30000ms
    }
    @Test
    public void testTimeForSize1000DenseAllPairs(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(1000, new FloydWarshall(), false, true);
        assertTrue(elapsedTime<=40000); // less than or equal 40000ms
    }
    @Test
    public void testTimeForSize1000DenseFromSource(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(1000, new FloydWarshall(), false, false);
        assertTrue(elapsedTime<=40000); // less than or equal 40000ms
    }

    @Test
    public void testOnePathsMediumGraphSizePositiveWeights(){
        Graph graph = new Graph("graph_files\\medium-1-positive.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        assertTrue(graph.calculateShortestPaths());
        assertEquals(algorithm.getPath(0,5),Arrays.asList(0,3,6,5));
        assertEquals(algorithm.getCost(0,5),6);
        assertEquals(algorithm.getPath(2,6),Arrays.asList(2,0,3,6));
        assertEquals(algorithm.getCost(2,6),9);
        assertEquals(algorithm.getPath(1,6),Arrays.asList(1,3,6));
        assertEquals(algorithm.getCost(1,6),7);
    }
    @Test
    public void testTwoPathsMediumGraphSizePositiveWeights(){
        Graph graph = new Graph("graph_files\\medium-2-positive.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        assertTrue(graph.calculateShortestPaths(0));
        assertEquals(algorithm.getCost(8,5), Long.MAX_VALUE); // unreachable
        assertEquals(algorithm.getCost(0,3), 2);
        assertEquals(algorithm.getPath(0,3),Arrays.asList(0,3));
        assertEquals(algorithm.getCost(0,5), 53);
        assertEquals(algorithm.getPath(0,5),Arrays.asList(0,3,5));
    }
    @Test
    public void testPathsLargeGraphSizePositiveWeights(){
        // should give same results as testOneMediumPositive
        Graph graph = new Graph("graph_files\\large-1-positive.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        assertTrue(graph.calculateShortestPaths());
        assertEquals(algorithm.getPath(0,5),Arrays.asList(0,3,6,5));
        assertEquals(algorithm.getCost(0,5),6);
        assertEquals(algorithm.getPath(2,6),Arrays.asList(2,0,3,6));
        assertEquals(algorithm.getCost(2,6),9);
        assertEquals(algorithm.getPath(1,6),Arrays.asList(1,3,6));
        assertEquals(algorithm.getCost(1,6),7);
    }
    @Test
    public void testOnePathsMediumGraphSizeNegativeWeights(){
        Graph graph = new Graph("graph_files\\medium-1-negative.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        assertTrue(graph.calculateShortestPaths());
        List<List<Long>> expected = List.of(List.of(0L,1L,-3L,2L,-4L),
                                                List.of(3L,0L,-4L,1L,-1L),
                                                List.of(7L,4L,0L,5L,3L),
                                                List.of(2L,-1L,-5L,0L,-2L),
                                                List.of(8L,5L,1L,6L,0L));
        for (int i = 0; i < graph.getNumberOfNodes(); i++){
            for (int j = 0; j < graph.getNumberOfNodes(); j++){
                assertEquals(expected.get(i).get(j).longValue(),algorithm.getCost(i,j));
            }
        }
        assertEquals(Arrays.asList(4,3,2,1),algorithm.getPath(4,1));
    }
    @Test
    public void testPathsLargeGraphSizeNegativeWeights(){
        // should give same results as testOneMediumNegative
        Graph graph = new Graph("graph_files\\large-1-negative.txt");
        Algorithm algorithm = new FloydWarshall();
        graph.setAlgorithm(algorithm);
        assertTrue(graph.calculateShortestPaths());
        List<List<Long>> expected = List.of(List.of(0L,1L,-3L,2L,-4L),
                List.of(3L,0L,-4L,1L,-1L),
                List.of(7L,4L,0L,5L,3L),
                List.of(2L,-1L,-5L,0L,-2L),
                List.of(8L,5L,1L,6L,0L));
        int originalOneSize=5;
        for (int i = 0; i < originalOneSize; i++){
            for (int j = 0; j < originalOneSize; j++){
                assertEquals(expected.get(i).get(j).longValue(),algorithm.getCost(i,j));
            }
        }
        assertEquals(Arrays.asList(4,3,2,1),algorithm.getPath(4,1));
    }
}
