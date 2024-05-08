package test;

import main.Algorithm;
import main.Dijkstra;
import main.Graph;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
}
