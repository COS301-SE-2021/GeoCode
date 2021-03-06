package tech.geocodeapp.geocode.mission.decorator;

import tech.geocodeapp.geocode.geocode.model.GeoPoint;

import java.util.UUID;

public interface MissionComponent {

    /**
     * gets the id of a mission
     * @return the UUID of the mission
     */
    UUID getId();

    /**
     *
     * @param id the id
     */
    void setId(UUID id);

    Integer getAmount();

    void setAmount(Integer amount);

    GeoPoint getLocation();

    void setLocation(GeoPoint location);

    Integer getCompletion();

    void setCompletion(Integer completion);

    /**
     * Calculates the distance traveled from the last location
     */
    void calculateDistance(GeoPoint newLocation);

    /**
     * Checks if the mission is complete
     * @return true if the mission is complete and false if it isn't
     */
    boolean checkIfFinished();

    /**
     * checks if the newLocation finishes the mission
     */
    void checkLocation(GeoPoint newLocation);
}
