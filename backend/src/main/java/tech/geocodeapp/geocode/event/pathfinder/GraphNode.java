package tech.geocodeapp.geocode.event.pathfinder;

import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class GraphNode {

    private final UUID geocodeID;
    private final GeoPoint location;
    private final List<GraphEdge> neighbours = new ArrayList<GraphEdge>();
    private boolean visited = false;

    /* A geocode in the event */
    GraphNode(GeoCode geocode) {
        this.geocodeID = geocode.getId();
        this.location = geocode.getLocation();
    }

    /* The start location */
    GraphNode(GeoPoint location) {
        this.geocodeID = null;
        this.location = location;
    }

    void addNeighbour(GraphEdge newEdge) {
        neighbours.add(newEdge);
    }

    GeoPoint getLocation() {
        return location;
    }

    List<UUID> getNearestNeighbourRecursive() {
        /* don't let the path visit this node again */
        this.visited = true;

        GraphEdge lowestDistance = null;
        /* Loop through neighbours */
        for (GraphEdge e: neighbours) {
            GraphNode neighbour = e.getDestination(this);

            /* if the neighbour is not visited, and is closer to this node than all other neighbours are, save it */
            if (!neighbour.visited && (lowestDistance == null || e.getDistance() < lowestDistance.getDistance())) {
                lowestDistance = e;
            }
        }

        List<UUID> output;
        if (lowestDistance == null) {
            /* This will be hit if there are no unvisited neighbours remaining */
            /* (i.e. this is the last node in the path) */
            /* Create a new ArrayList that will contain the path */
            output = new ArrayList<UUID>();
        } else {
            /* Get the ArrayList created further down the path */
            output = lowestDistance.getDestination(this).getNearestNeighbourRecursive();
        }

        /* If the geocode ID is null, that is the start point (which does not contain a geocode) */
        /* The output list should only contain non-null values */
        if (this.geocodeID != null) {
            /* Add the geocode ID to the start of the list. Through recursion, the list is populated from end to start. */
            output.add(0, this.geocodeID);
        }

        /* Return the output list */
        return output;
    }
}