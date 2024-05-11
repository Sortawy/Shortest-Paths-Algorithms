package main;

public class Edge {
    private long weight;
    private int destination;

    public Edge(int destination, long weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public int getDestination() {
        return destination;
    }

    public long getWeight() {
        return weight;
    }
}
