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
                case 1 -> {
                    System.out.print("Enter the source node: ");
                    int source = Integer.parseInt(in.nextLine());
                    enterPathFromSingleSourceMenu(source);
                }
                case 2 -> enterAllPairShortestPathMenu();
                case 3 -> enterCheckNegativeCycleMenu();
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
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

    public static void printAlgorithmsMenu() {
        System.out.println("1. Dijkstra's Algorithm");
        System.out.println("2. Bellman-Ford Algorithm");
        System.out.println("3. Floyd-Warshall Algorithm");
        System.out.println("4. Go back to previous menu");
        System.out.print("Enter your choice: ");
    }

    public static void printPathOrCostOptionsMenu() {
        System.out.println("1. Print the shortest path");
        System.out.println("2. Print the cost of the shortest path");
        System.out.println("3. Go back to previous menu");
        System.out.print("Enter your choice: ");
    }

    public static void enterPathFromSingleSourceMenu(int source) {
        Scanner in = new Scanner(System.in);
        while (true) {
            printAlgorithmsMenu();
            int choice = Integer.parseInt(in.nextLine());
            if (choice == 4)
                return;
            System.out.print("Enter the destination node: ");
            int destination = Integer.parseInt(in.nextLine());

            setAlgorithm(choice);
            graph.calculateShortestPaths(source);

            while (true) {
                printPathOrCostOptionsMenu();
                int subChoice = Integer.parseInt(in.nextLine());
                if (subChoice == 3) {
                    break;
                } else {
                    printAnswer(subChoice, source, destination);
                }
            }
        }
    }

    public static void enterAllPairShortestPathMenu() {
        Scanner in = new Scanner(System.in);
        while (true) {
            printAlgorithmsMenu();
            int choice = Integer.parseInt(in.nextLine());
            if (choice == 4)
                return;

            setAlgorithm(choice);
            graph.calculateShortestPaths();

            while (true) {
                System.out.print("Enter the source node: ");
                int source = Integer.parseInt(in.nextLine());
                System.out.print("Enter the destination node: ");
                int destination = Integer.parseInt(in.nextLine());
                printPathOrCostOptionsMenu();
                int subChoice = Integer.parseInt(in.nextLine());
                if (subChoice == 3) {
                    break;
                } else {
                    printAnswer(subChoice, source, destination);
                }
            }
        }
    }

    public static void enterCheckNegativeCycleMenu() {
        Scanner in = new Scanner(System.in);
        while (true) {
            printAlgorithmsMenu();
            int choice = Integer.parseInt(in.nextLine());
            if (choice == 3)
                return;

            setAlgorithm(choice);

            if (!graph.calculateShortestPaths()) {
                System.out.println("Graph contains a negative cycle.");
            } else {
                System.out.println("Graph does not contain a negative cycle.");
            }
        }
    }

    public static void setAlgorithm(int choice) {
        switch (choice) {
            case 1 -> algorithm = new Dijkstra();
            case 2 -> algorithm = new BellmanFord();
            case 3 -> algorithm = new FloydWarshall();
            default -> System.out.println("Invalid choice.");
        }
        graph.setAlgorithm(algorithm);
    }

    public static void printAnswer(int choice, int source, int destination) {
        if (choice == 1) {
            printPath(source, destination);
        }else if (choice == 2) {
            printCost(source, destination);
        }else {
            System.out.println("Invalid choice.");
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