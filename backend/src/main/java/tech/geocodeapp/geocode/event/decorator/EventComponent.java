package tech.geocodeapp.geocode.event.decorator;

import tech.geocodeapp.geocode.collectable.model.CollectableSet;
import tech.geocodeapp.geocode.collectable.model.Rarity;
import tech.geocodeapp.geocode.event.model.UserEventStatus;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.model.Point;

import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public interface EventComponent {

    /**
     * Getters and setters for all variables that the concrete component includes
     */

    UUID getID();

    void setID(UUID id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    GeoPoint getLocation();

    void setLocation(GeoPoint location);

    List<UUID> getGeocodeIDs();

    void setGeocodeIDs(List<UUID> geocodeIDs);

    LocalDate getBeginDate();

    void setBeginDate(LocalDate date);

    LocalDate getEndDate();

    void setEndDate(LocalDate date);

    List<Leaderboard> getLeaderboards();

    void setLeaderboards(List<Leaderboard> leaderboards);

    Boolean isAvailable();

    void setAvailable(Boolean available);

    /**
     * Getter for time trials used to get the timeLimit variable from {@link TimeTrialEventDecorator}
     */
    Integer getTimeLimit();

    /**
     * Setter for time trials used to set the timeLimit variable from {@link TimeTrialEventDecorator}
     */
    void setTimeLimit(Integer limit);

    /**
     * Function to record information in the status object when the user starts an event
     */
    void handleEventStart(UserEventStatus status);

    /**
     * Function to record information in the status object when the user completes a stage
     */
    void handleStageCompletion(int stageNumber, UserEventStatus status);

    /**
     * Function to record information in the status object when the user finishes an event
     */
    void handleEventEnd(UserEventStatus status);

    /**
     * Function to calculate the number of points a user should receive for finding the provided geocode.
     */
    int calculatePoints(GeoCode foundGeocode, UserEventStatus status);

}
