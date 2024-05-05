package main;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static Graph graph;
    public static Algorithm algorithm;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the path of the graph structure: ");
        String filePath = in.nextLine();
        graph = new Graph(filePath);

        while (true) {
            printMainMenu();
            int choice = Integer.parseInt(in.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Enter the source node: ");
                    int source = Integer.parseInt(in.nextLine());
                    EnterSubMenu(source);
                    break;
                case 2:

                case 3:
                    EnterSubMenu2();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void printMainMenu() {
        System.out.println("1. Finds the shortest paths from source node to any other node.");
        System.out.println("2. Finds the shortest paths between all the pairs of nodes.");
        System.out.println("3. Check if the graph contains a negative cycle.");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void EnterSubMenu(int source) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("1. Dijkstra's Algorithm");
            System.out.println("2. Bellman-Ford Algorithm");
            System.out.println("3. Floyd-Warshall Algorithm");
            System.out.println("4. Go back To Main Menu");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(in.nextLine());
            if (choice == 4)
                return;
            System.out.print("Enter the destination node: ");
            int destination = Integer.parseInt(in.nextLine());
            switch (choice) {
                case 1: {
                    // graph.dijkstra(source);
                    algorithm = new Dijkstra();
                    graph.setAlgorithm(algorithm);
                    graph.calculateShortestPaths(source);
                    printSubMenu();
                    int subChoice = Integer.parseInt(in.nextLine());
                    if (subChoice == 1) {
                        printPath(source, destination);
                    } else if (subChoice == 2) {
                        printCost(source, destination);
                    }
                    break;
                }
                case 2: {
                    // graph.bellman(source, destination);
                    algorithm = new BellmanFord();
                    graph.setAlgorithm(algorithm);
                    graph.calculateShortestPaths(source);
                    printSubMenu();
                    int subChoice = Integer.parseInt(in.nextLine());
                    if (subChoice == 1) {
                        // use return value of bellman to print the path
                        printPath(source, destination);
                    } else if (subChoice == 2) {
                        // use return value of bellman to print the cost
                        printCost(source, destination);
                    }
                    break;
                }
                case 3: { // chosen floyd algorithm
                    // graph.floyd(source, destination);
                    algorithm = new FloydWarshall();
                    graph.setAlgorithm(algorithm);
                    graph.calculateShortestPaths(source);
                    printSubMenu();
                    int subChoice = Integer.parseInt(in.nextLine());
                    if (subChoice == 1) {
                        printPath(source, destination);
                    } else if (subChoice == 2) {
                        printCost(source, destination);
                    }
                    break;
                }
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    public static void printSubMenu() {
        System.out.println("1. Print the shortest path");
        System.out.println("2. Print the cost of the shortest path");
        System.out.print("Enter your choice: ");
    }

    //checking negative cycle menu   
    public static void EnterSubMenu2() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("1. Bellman-Ford Algorithm");
            System.out.println("2. Floyd-Warshall Algorithm");
            System.out.println("3. Go back To Main Menu");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(in.nextLine());
            if (choice == 3)
                return;
            switch (choice) {
                case 1:
                    // call method to check negative cycle using bellman
                    algorithm = new BellmanFord();
                    graph.setAlgorithm(algorithm);
                    // // if(graph.containsNegativeCycle())
                    //     System.out.println("Graph contains negative cycle.");
                    // else
                    //     System.out.println("Graph does not contain negative cycle.");
                    break;
                case 2:
                    // call method to check negative cycle using floyd
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void printPath(int u, int v) {
        List<Integer> path = algorithm.getPath(u, v);
        if (path == null) {
            System.out.println("No path exists between " + u + " and " + v);
            return;
        }
        System.out.print("Shortest path between " + u + " and " + v + " is: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i != path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public static void printCost(int u, int v) {
        int cost = algorithm.getCost(u, v);
        if (cost == Integer.MAX_VALUE) {
            System.out.println("No path exists between " + u + " and " + v);
            return;
        }
        System.out.println("Shortest path cost between " + u + " and " + v + " is: " + cost);
    }
}