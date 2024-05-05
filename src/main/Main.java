package main;

import java.util.Scanner;

public class Main {
    public static Graph graph;
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
            if (choice == 4) return;
            System.out.print("Enter the destination node: ");
            int destination = Integer.parseInt(in.nextLine());
            switch (choice) {
                case 1: {
                    // graph.dijkstra(source);
                    printSubMenu();
                    int subChoice = Integer.parseInt(in.nextLine());
                    if (subChoice == 1) {
                        // use return value of dijkstra to print the path to dest
                    } else if (subChoice == 2) {
                       // use return value of dijkstra to print the cost to dest
                    }
                    break;
                }
                case 2: {
                    // graph.bellman(source, destination);
                    printSubMenu();
                    int subChoice = Integer.parseInt(in.nextLine());
                    if (subChoice == 1) {
                        // use return value of bellman to print the path
                    } else if (subChoice == 2) {
                       // use return value of bellman to print the cost
                    }
                    break;
                }
                case 3: {
                    // graph.floyd(source, destination);
                    graph.calculateShortestPaths("floyd", destination);
                    printSubMenu();
                    int subChoice = Integer.parseInt(in.nextLine());
                    if (subChoice == 1) {
                        // use return value of floyd to print the path
                        graph.printFloydPath(source,destination);
                    } else if (subChoice == 2) {
                       // use return value of floyd to print the cost
                        graph.printFloydCost(source,destination);
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

    public static void EnterSubMenu2() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("1. Bellman-Ford Algorithm");
            System.out.println("2. Floyd-Warshall Algorithm");
            System.out.println("3. Go back To Main Menu");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(in.nextLine());
            if (choice == 3) return;
            switch (choice) {
                case 1:
                    // call method to check negative cycle using bellman
                    break;
                case 2:
                    // call method to check negative cycle using floyd
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}