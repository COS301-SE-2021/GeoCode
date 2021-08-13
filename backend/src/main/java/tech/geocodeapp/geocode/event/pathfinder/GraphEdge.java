package tech.geocodeapp.geocode.event.pathfinder;

class GraphEdge {

    private final GraphNode node1;
    private final GraphNode node2;
    private final double distance;

    GraphEdge(GraphNode node1, GraphNode node2) {
        this.node1 = node1;
        this.node2 = node2;
        this.distance = node1.getLocation().distanceTo(node2.getLocation());
    }

    double getDistance() {
        return distance;
    }

    GraphNode getDestination(GraphNode source) {
        if (node1 == source) return node2;
        else if (node2 == source) return node1;
        else return null;
    }
}