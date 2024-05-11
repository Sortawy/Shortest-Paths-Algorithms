# Shortest Paths Algorithms

# Dijkstra Algorithm

This algorithm finds shortest paths from the source to all other nodes in the graph, producing a shortest path tree. Its time complexity is $O(V + E\; log\; V)$ but can reach less than that when using priority queue.
Dijkstra algorithm can’t handle negative weights. But, it is asymptotically the fastest known single-source shortest path algorithm for arbitrary directed graphs with unbounded non-negative weights.

## Time Complexity : $O(V + E\; log\; V)$

## Space Complexity : $O(V)$

# Bellman-Ford Algorithm

The Bellman-Ford algorithm is an algorithm that computes shortest paths from a single source vertex to all of the other vertices in a weighted directed graph. 
It is capable of handling graphs in which some of the edge weights are negative numbers. 
It works in O(V ∗ E) time and O(V ) space complexities where V is the number of vertices and E is the number of edges in the graph. It can detect the presence of a negative cycle in the graph.

## Time Complexity : $O(V ∗ E)$

## Space Complexity : $O(V)$

# Floyd-Warshall Algorithm

The Floyd–Warshall algorithm is an algorithm for finding shortest paths in a directed weighted graph with positive or negative edge weights. A single execution of the algorithm will find the lengths of shortest paths between all pairs of vertices.
It works in O(V^3) time complexity. It can detect the presence of a negative cycle in the graph.

## Time Complexity : $O$( $V^3$ )

## Space Complexity : $O$( $V^2$ )
# Comparison between Algorithms

## Mean Time to get the shortest path between 2 specific nodes (ms)

| Graph Size | Graph Density | Dijkstra | Bellman-Ford | Floyd-Warshall |
| --- | --- | --- | --- | --- |
| 10 | Sparse | 0.01 | 0.03 | 0.04 |
| 10 | Dense | 0.02 | 0.04 | 0.05 |
| 30 | Sparse | 0.00 | 0.03 | 0.19 |
| 30 | Dense | 0.11 | 0.10 | 0.43 |
| 50 | Sparse | 0.00 | 0.05 | 0.25 |
| 50 | Dense | 0.13 | 0.46 | 0.42 |
| 100 | Sparse | 0.00 | 0.15 | 0.81 |
| 100 | Dense | 0.56 | 3.17 | 1.99 |
| 500 | Sparse | 0.01 | 3.98 | 78.45 |
| 500 | Dense | 10.36 | 978.67 | 164.09 |
| 1000 | Sparse | 0.02 | 15.59 | 594.52 |
| 1000 | Dense | 57.80 | x | 1216.22 |

## Mean Time to get the shortest path between all pairs of nodes (ms)

| Graph Size | Graph Density | Dijkstra | Bellman-Ford | Floyd-Warshall |
| --- | --- | --- | --- | --- |
| 10 | Sparse | 0.12 | 0.32 | 0.04 |
| 10 | Dense | 0.23 | 0.36 | 0.05 |
| 30 | Sparse | 0.04 | 0.70 | 0.20 |
| 30 | Dense | 2.89 | 2.54 | 0.43 |
| 50 | Sparse | 0.05 | 2.01 | 0.26 |
| 50 | Dense | 6.97 | 19.88 | 0.44 |
| 100 | Sparse | 0.07 | 13.24 | 0.88 |
| 100 | Dense | 56.58 | 314.67 | 2.08 |
| 500 | Sparse | 1.37 | 1881.79 | 80.91 |
| 500 | Dense | 7203.32 | 661665.36 | 162.42 |
| 1000 | Sparse | 3.02 | 16333.73 | 599.27 |
| 1000 | Dense | 75831.92 | x | 1187.51 |

---

## Contributors

- AbdElRahman Bassam
    
- AbdElRahman Osama
        
- Ahmed Youssef
        
- Mohamed Mahfouz
        
- Mourad Mahgoub