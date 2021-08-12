package tech.geocodeapp.geocode.event.decorator;

import tech.geocodeapp.geocode.collectable.model.CollectableSet;
import tech.geocodeapp.geocode.collectable.model.Rarity;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public class ConcreteEvent implements EventComponent {

    private UUID id;
    private String name;
    private String description;
    private GeoPoint location;
    private List<UUID> geocodeIDs;
    private LocalDate beginDate;
    private LocalDate endDate;
    private List<Leaderboard> leaderboards;
    private Boolean available;

    public ConcreteEvent() { }

    @Override
    public UUID getID() { return id; }

    @Override
    public void setID(UUID id) { this.id = id; }

    @Override
    public String getName() { return name; }

    @Override
    public void setName(String name) { this.name = name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public void setDescription(String description) { this.description = description; }

    @Override
    public GeoPoint getLocation() { return location; }

    @Override
    public void setLocation(GeoPoint location) { this.location = location; }

    @Override
    public List<UUID> getGeocodeIDs() { return geocodeIDs; }

    @Override
    public void setGeocodeIDs(List<UUID> geocodeIDs) { this.geocodeIDs = geocodeIDs; }

    @Override
    public LocalDate getBeginDate() { return beginDate; }

    @Override
    public void setBeginDate(LocalDate date) { this.beginDate = date; }

    @Override
    public LocalDate getEndDate() { return endDate; }

    @Override
    public void setEndDate(LocalDate date) { this.endDate = date; }

    @Override
    public List<Leaderboard> getLeaderboards() { return leaderboards; }

    @Override
    public void setLeaderboards(List<Leaderboard> leaderboards) { this.leaderboards = leaderboards; }

    @Override
    public Boolean isAvailable() { return available; }

    @Override
    public void setAvailable(Boolean available) { this.available = available; }

    /**
     * @return null because timeLimit is not contained in the concreteComponent
     */
    @Override
    public Integer getTimeLimit() { return null; }

    /**
     * Do nothing because timeLimit is not contained in the concreteComponent
     * @param limit unused
     */
    @Override
    public void setTimeLimit(Integer limit) { }
}
