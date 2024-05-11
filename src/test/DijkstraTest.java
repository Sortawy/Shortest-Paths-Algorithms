package test;

import main.Algorithm;
import main.Dijkstra;
import main.Dijkstra;
import main.Graph;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DijkstraTest {
    @Test
    public void simpleTest() {
        Graph graph = new Graph("graph_files\\Dijkstra1.txt");
        Algorithm algorithm = new Dijkstra();
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
        Algorithm algorithm = new Dijkstra();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(4);
        List<Integer> path = algorithm.getPath(4, 2);
        assertNull(path);
        assertEquals(Long.MAX_VALUE, algorithm.getCost(4, 2));
    }

    @Test
    public void NegativeWeightTest() {
        Graph graph = new Graph("graph_files\\graph2.txt");
        Algorithm algorithm = new Dijkstra();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(0);
        List<Integer> path = algorithm.getPath(0, 2);
        List<Integer> expectedPath = Arrays.asList(0, 2);
        assertEquals(expectedPath, path);
        assertEquals(7, algorithm.getCost(0, 2));
    }
    @Test
    public void NegativeWeightWrongTest() {
        Graph graph = new Graph("graph_files\\graph2.txt");
        Algorithm algorithm = new Dijkstra();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(0);
        List<Integer> path = algorithm.getPath(0, 6);
        List<Integer> expectedPath = Arrays.asList(0, 1, 3, 5, 6);
        assertEquals(expectedPath, path);
        assertEquals(8, algorithm.getCost(0, 6));
    }

    @Test
    public void LargetTest() {
        Graph graph = new Graph("graph_files\\Dijkstra2.txt");
        Algorithm algorithm = new Dijkstra();
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
        Algorithm algorithm = new Dijkstra();
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
        Algorithm algorithm = new Dijkstra();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(0);
        List<Integer> path = algorithm.getPath(0, 0);
        List<Integer> expectedPath = List.of(0);
        assertEquals(expectedPath, path);
        assertEquals(0, algorithm.getCost(0, 0));
    }

    @Test
    public void DisconnectedGraphTest() {
        Graph graph = new Graph("graph_files\\Disconnected.txt");
        Algorithm algorithm = new Dijkstra();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(95);
        List<Integer> path = algorithm.getPath(95, 0);
        assertNull(path);
        assertEquals(Long.MAX_VALUE, algorithm.getCost(95, 0));
    }

    @Test
    public void DuplicatesGraphTest() {
        Graph graph = new Graph("graph_files\\Duplicates.txt");
        Algorithm algorithm = new Dijkstra();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths();
        List<Integer> path = algorithm.getPath(2, 5);
        List<Integer> expectedPath = Arrays.asList(2, 3, 4, 5);
        assertEquals(expectedPath, path);
        assertEquals(-2, algorithm.getCost(2, 5));
    }

    @Test
    public void NotCalculatedTest() {
        Graph graph = new Graph("graph_files\\Dijkstra1.txt");
        Algorithm algorithm = new Dijkstra();
        graph.setAlgorithm(algorithm);
        graph.calculateShortestPaths(2);
        List<Integer> path = algorithm.getPath(1, 0);
        assertNull(path);
        assertEquals(Long.MAX_VALUE, algorithm.getCost(1, 0));
    }
    @Test
    public void simpleCasePositiveSelfLoop(){
        Graph graph = new Graph("graph_files\\positive-selfloop.txt");
        Algorithm algorithm = new Dijkstra();
        graph.setAlgorithm(algorithm);
        assertTrue(graph.calculateShortestPaths(1));
        assertEquals(algorithm.getPath(1,1),Arrays.asList(1));
    }
    @Test
    public void testCaseWithSmallSize(){
        Graph graph = new Graph("graph_files\\graph3.txt");
        Algorithm algorithm = new Dijkstra();
        graph.setAlgorithm(algorithm);
        assertTrue(graph.calculateShortestPaths(1));
        assertEquals(algorithm.getCost(1,9),500);
        assertEquals(algorithm.getCost(1,10),Long.MAX_VALUE);
    }

    @Test
    public void testTimeForSize100SparseAllPairs(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(100, new Dijkstra(), true, true);
        assertTrue(elapsedTime<=10); // less than or equal 10ms
    }
    @Test
    public void testTimeForSize100SparseFromSource(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(100, new Dijkstra(), true, false);
        assertTrue(elapsedTime<=10); // less than or equal 10ms
    }
    @Test
    public void testTimeForSize100DenseAllPairs(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(100, new Dijkstra(), false, true);
        assertTrue(elapsedTime<=1000); // less than or equal 1000ms
    }
    @Test
    public void testTimeForSize100DenseFromSource(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(100, new Dijkstra(), false, false);
        assertTrue(elapsedTime<=10); // less than or equal 10ms
    }
    @Test
    public void testTimeForSize500SparseAllPairs(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(500, new Dijkstra(), true, true);
        assertTrue(elapsedTime<=3000); // less than or equal 3000ms
    }
    @Test
    public void testTimeForSize500SparseFromSource(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(500, new Dijkstra(), true, false);
        assertTrue(elapsedTime<=300); // less than or equal 300ms
    }
    @Test
    public void testTimeForSize500DenseFromSource(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(500, new Dijkstra(), false, false);
        assertTrue(elapsedTime<=600); // less than or equal 600ms
    }
    @Test
    public void testTimeForSize1000SparseFromSource(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(1000, new Dijkstra(), true, false);
        assertTrue(elapsedTime<=1200); // less than or equal 1200ms
    }
    @Test
    public void testTimeForSize1000DenseFromSource(){
        long elapsedTime = TestSupport.testRandomGraphOfSize(1000, new Dijkstra(), false, false);
        assertTrue(elapsedTime<=2700); // less than or equal 2700ms
    }

    @Test
    public void testOnePathsMediumGraphSizePositiveWeights(){
        Graph graph = new Graph("graph_files\\medium-1-positive.txt");
        Algorithm algorithm = new Dijkstra();
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
        Algorithm algorithm = new Dijkstra();
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
        Algorithm algorithm = new Dijkstra();
        graph.setAlgorithm(algorithm);
        assertTrue(graph.calculateShortestPaths());
        assertEquals(algorithm.getPath(0,5),Arrays.asList(0,3,6,5));
        assertEquals(algorithm.getCost(0,5),6);
        assertEquals(algorithm.getPath(2,6),Arrays.asList(2,0,3,6));
        assertEquals(algorithm.getCost(2,6),9);
        assertEquals(algorithm.getPath(1,6),Arrays.asList(1,3,6));
        assertEquals(algorithm.getCost(1,6),7);
    }
}
