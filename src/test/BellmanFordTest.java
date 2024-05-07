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
       Graph graph = new Graph("graph_files\\negativeCycleSmall.txt");
       Algorithm algorithm = new BellmanFord();
       graph.setAlgorithm(algorithm);
       boolean isNegativeCycle=!graph.calculateShortestPaths();
       assertEquals(true, isNegativeCycle);   
   }
   @Test
   public void negativeCycleSmallTest() {
       Graph graph = new Graph("graph_files\\negativeCycleSmall.txt");
       Algorithm algorithm = new BellmanFord();
       graph.setAlgorithm(algorithm);
       boolean isNegativeCycle=!graph.calculateShortestPaths();
       assertEquals(true, isNegativeCycle);   
   }

}
