package tech.geocodeapp.geocode.event.decorator;

import tech.geocodeapp.geocode.collectable.model.CollectableSet;
import tech.geocodeapp.geocode.collectable.model.Rarity;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

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

}
