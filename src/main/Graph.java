package main;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Graph {
    private Map<Integer, List<Edge>> graph;
    private int [][] costMatrix;
    private int numberOfNodes;
    private int numberOfEdges;
    private int[][] floydMinimumCosts;
    private List<Integer>[][] floydAllPaths;
    public Graph(String filePath) {
        try {
            List<String> rows = Files.readAllLines(Paths.get(filePath));
            for (String row : rows) {
                if (row.isEmpty()) continue;
                String[] line = row.split(" ");
                if (line.length == 2 && numberOfNodes == 0) {
                    numberOfNodes = Integer.parseInt(line[0]);
                    numberOfEdges = Integer.parseInt(line[1]);
                    graph = new HashMap<>(numberOfNodes);
                    costMatrix =new int[numberOfNodes][numberOfNodes];
                    for (int i =0;i<numberOfNodes;i++)
                        for (int j =0;j<numberOfNodes;j++)
                            costMatrix[i][j]=Integer.MAX_VALUE;
                }
                else if (line.length == 3) {
                    int source = Integer.parseInt(line[0]);
                    int destination = Integer.parseInt(line[1]);
                    int weight = Integer.parseInt(line[2]);
                    addEdge(source, destination, weight);
                    costMatrix[source][destination]=weight;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static class Edge {
        private int weight;
        private int destination;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public int getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }
    private void addEdge(int source, int destination, int weight) {
        if (source >= numberOfNodes || destination >= numberOfNodes) {
            return;
        }
        Edge edge = new Edge(destination, weight);
        graph.putIfAbsent(source, new ArrayList<>());
        graph.putIfAbsent(destination, new ArrayList<>());
        graph.get(source).add(edge);
    }
    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    /**
     * Method takes requested algorithm name, applies the algorithm
     * @param algorithm : Name of algorithm requested
     * @return a boolean : True indicates negative cycles, BUT this return value is redundant
     *                  for Dijkstra algorithm and shouldn't be used.
     */
    public boolean calculateShortestPaths(String algorithm){
        if (algorithm.equals("dijkstra")){
            // call dijkstra of all pairs
        }
        else if (algorithm.equals("bellman")){
            // call bellmanford of all pairs
        }
        else if (algorithm.equals("floyd")) {
            // call floyd
            // if floydCosts[source][destination] is null: (like memoization)
            if (this.floydMinimumCosts == null) {
                int[][] nextNode = new int[numberOfNodes][numberOfNodes];
                return floydWarshall(this.costMatrix, nextNode);
            }
        }
        return false;
    }

    /**
     * Function takes requested algorithm name, the source node and applies the algorithm
     * @param algorithm : Name of algorithm requested
     * @param source : The source node of the path
     * @return a boolean : True indicates negative cycles, BUT this return value is redundant
     *                  for Dijkstra algorithm and shouldn't be used.
     */
    public boolean calculateShortestPaths(String algorithm, int source){
        if (algorithm.equals("dijkstra")){
            // call dijkstra of all pairs
        }
        else if (algorithm.equals("bellman")){
            // call bellmanford of all pairs
        }
        else if (algorithm.equals("floyd")) {
            // call floyd
            // if floydCosts[source][destination] is null: (like memoization)
            if (this.floydMinimumCosts == null) {
                int[][] nextNode = new int[numberOfNodes][numberOfNodes];
                return floydWarshall(this.costMatrix, nextNode);
            }
        }
        return false;
    }
   /*================Floyd================*/
    public void constructFloydPaths(int[][] nextNode) {
        if (this.floydAllPaths != null) return;
        this.floydAllPaths=new ArrayList[numberOfNodes][numberOfNodes];
        for (int u =0;u<numberOfNodes;u++){
            for (int v = 0; v < numberOfNodes;v++){
                if (nextNode[u][v]==-1){
                    this.floydAllPaths[u][v]= new ArrayList<>();
                }
                else {
                    int temp = u;
                    List<Integer> path= new ArrayList<>();
                    path.add(u);
                    while (temp!=v){
                        temp=nextNode[temp][v];
                        path.add(temp);
                    }
                    this.floydAllPaths[u][v]=path;
                }
            }
        }
    }
    public void setupFloydMatrices(int [][] floydCostMatrix, int[][] nextNode){
        this.floydMinimumCosts=floydCostMatrix;
        for (int i =0;i<numberOfNodes;i++){
            for (int j =0;j<numberOfNodes;j++){
                if (i==j)
                    this.floydMinimumCosts[i][j]=0;
                else
                    this.floydMinimumCosts[i][j]=floydCostMatrix[i][j];
                if(floydCostMatrix[i][j]!= Integer.MAX_VALUE){
                    nextNode[i][j]=j;
                }
                else {
                    nextNode[i][j]=-1;
                }
            }
        }
    }
    public boolean floydWarshall(int[][] floydCostMatrix, int [][] nextNode) {
        setupFloydMatrices(floydCostMatrix,nextNode);
        for (int i = 0;i < numberOfNodes;i ++){
            for (int j = 0;j < numberOfNodes;j ++){
                if(j==i)continue;
                for (int k =0; k< numberOfNodes;k++) {
                    if (k == i) continue;
                    if (this.floydMinimumCosts[j][i] != Integer.MAX_VALUE && this.floydMinimumCosts[i][k] != Integer.MAX_VALUE
                        && (this.floydMinimumCosts[j][i] + this.floydMinimumCosts[i][k] < this.floydMinimumCosts[j][k]) ){
                        this.floydMinimumCosts[j][k] = this.floydMinimumCosts[j][i] + this.floydMinimumCosts[i][k];
                        nextNode[j][k] = nextNode[j][i];
                    }
                }
            }
        }

        /* Now detect negative cycles */
        constructFloydPaths(nextNode);
        for (int i = 0; i < numberOfNodes; i++)
            if (this.floydMinimumCosts[i][i] < 0)
                return false;
        return true;
    }
    // Used to print one pair of floyd's shortest path
    public void printFloydPath(int u, int v){
        List<Integer> path = this.floydAllPaths[u][v];
        if (path.isEmpty()){
            System.out.println("There is no path between "+u+" and "+v);
            return;
        }
        for (int i = 0; i < path.size();i++){
            System.out.print(path.get(i));
            if (i!=path.size()-1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public void printFloydCost(int u, int v){
        System.out.println("Minimum Path Cost Between "+ u +" and " + v
                + " is " + this.floydMinimumCosts[u][v]);
    }
    /*================Floyd END================*/

    /*================Bellman================*/
    public boolean bellmanFordPaths(int source, int[] cost, int[] parent){
       
        for (int i = 0; i < numberOfNodes; i++) {
            cost[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        cost[source] = 0;

        for (int i = 0; i < numberOfNodes - 1; i++) {
            for (int src = 0; src < numberOfNodes; src++) {
                for (Edge edge : graph.get(src)) {
                    int dest = edge.getDestination();
                    int weight = edge.getWeight();
                    if (cost[src] != Integer.MAX_VALUE && cost[src] + weight < cost[dest]) {
                        cost[dest] = cost[src] + weight;
                        parent[dest] = src;
                    }
                }
            }
        }
        for (int src = 0; src < numberOfNodes; src++) {
            for (Edge edge : graph.get(src)) {
                int dest = edge.getDestination();
                int weight = edge.getWeight();
                if (cost[src] != Integer.MAX_VALUE && cost[src] + weight < cost[dest]) {
                    return false;
                }
            }
        }
        return true;
    }
    public List<Integer> printBellmanPath(int source, int destination, int[] parent){
        List<Integer> path = new ArrayList<>();
        int temp = destination;
        while (temp != source){
            path.add(temp);
            temp = parent[temp];
        }
        path.add(source);
        Collections.reverse(path);

        //just for testing 
        for (int i = 0; i < path.size(); i++){
            System.out.print(path.get(i));
            if (i+1 != path.size()){
                System.out.print(" -> ");
            }
        }
        System.out.println();
        return path;
    }
    public boolean isNegativeCycleBellmanDisconnected(){
        boolean visited[] = new boolean[numberOfNodes];
        Arrays.fill(visited, false);
        int cost[] = new int[numberOfNodes];
        int parent[] = new int[numberOfNodes];

        
        for (int i = 0; i < numberOfNodes; i++){
            if (!visited[i]){
                if (!bellmanFordPaths(i,cost,parent)){
                    return true;
                }
                for (int j = 0; j < numberOfNodes; j++)
                        if (cost[j] != Integer.MAX_VALUE)
                            visited[j] = true;
            }
        
        }
        return false;
    }
    /*================Bellman END================*/
}
