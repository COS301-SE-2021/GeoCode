package tech.geocodeapp.geocode.event.pathfinder;

import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Graph {

    public static List<UUID> getOptimalGeocodeIDOrder(List<GeoCode> geocodes, GeoPoint startLocation) {

        List<GraphNode> unvisitedNodes = new ArrayList<GraphNode>();

        /* The start location of the event */
        GraphNode start = new GraphNode(startLocation);
        unvisitedNodes.add(start);

        /* Construct the nodes of the graph */
        for (GeoCode g: geocodes) {
            GraphNode node = new GraphNode(g);
            unvisitedNodes.add(node);
        }

        /* Construct the edges between the nodes */
        for (int i = 0; i < unvisitedNodes.size(); i++) {
            GraphNode source = unvisitedNodes.get(i);
            /* The 1st node will construct edges to the 2nd, 3rd, ... nth node */
            /* The 2nd node will construct edges to the 3rd, 4th, ... nth node */
            /* The nth node will construct no edges as all previous nodes have already made links to it */
            for (int j = i+1; j < unvisitedNodes.size(); j++) {
                GraphNode destination = unvisitedNodes.get(j);
                GraphEdge edge = new GraphEdge(source, destination);
                source.addNeighbour(edge);
                destination.addNeighbour(edge);
            }
        }

        return start.getNearestNeighbourRecursive();
    }
}