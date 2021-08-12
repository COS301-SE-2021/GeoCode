package tech.geocodeapp.geocode.event.decorator;

import org.apache.tomcat.jni.Local;
import tech.geocodeapp.geocode.collectable.model.CollectableSet;
import tech.geocodeapp.geocode.collectable.model.Rarity;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public abstract class EventDecorator implements EventComponent {
    //the decorated EventComponent
    protected EventComponent decoratedType;

    /**
     * @param decoratedType the EventComponent to decorate
     */
    public EventDecorator(EventComponent decoratedType) {
        this.decoratedType = decoratedType;
    }

    /**
     * @return the value returned by decoratedType's getID() method
     */
    public UUID getID() {
        return decoratedType.getID();
    }

    /**
     * Sets the decoratedType's name variable
     * @param id the id of the Event
     */
    public void setID(UUID id) { decoratedType.setID(id); }

    /**
     * @return the value returned by decoratedType's getName() method
     */
    public String getName() {
        return decoratedType.getName();
    }

    /**
     * Sets the decoratedType's name variable
     * @param name the name of the Event
     */
    public void setName(String name) {
        decoratedType.setName(name);
    }

    /**
     * @return the value returned by decoratedType's getDescription() method
     */
    public String getDescription() {
        return decoratedType.getDescription();
    }

    /**
     * Sets the decoratedType's description variable
     * @param description the description of the Event
     */
    public void setDescription(String description) {
        decoratedType.setDescription(description);
    }

    /**
     * @return the decoratedType's rarity
     */
    public GeoPoint getLocation() {
        return decoratedType.getLocation();
    }

    /**
     * Sets the decoratedType's location
     * @param location the starting location of the Event
     */
    public void setLocation(GeoPoint location) {
        decoratedType.setLocation(location);
    }

    /**
     * @return the id of the decoratedType
     */
    public List<UUID> getGeocodeIDs() {
        return decoratedType.getGeocodeIDs();
    }

    /**
     * Sets the decoratedType's list of geocode IDs
     * @param ids the list of ids
     */
    public void setGeocodeIDs(List<UUID> ids) {
        decoratedType.setGeocodeIDs(ids);
    }

    /**
     * @return the starting date of the decoratedType
     */
    public LocalDate getBeginDate(){
        return decoratedType.getBeginDate();
    }

    /**
     * Sets the decoratedType's start date
     * @param date the start date of the Event
     */
    public void setBeginDate(LocalDate date){
        decoratedType.setBeginDate(date);
    }

    /**
     * @return the ending date of the decoratedType
     */
    public LocalDate getEndDate(){
        return decoratedType.getEndDate();
    }

    /**
     * Sets the decoratedType's end date
     * @param date the end date of the Event
     */
    public void setEndDate(LocalDate date){
        decoratedType.setEndDate(date);
    }

    /**
     * @return the list of Leaderboards of the decoratedType
     */
    public List<Leaderboard> getLeaderboards() {
        return decoratedType.getLeaderboards();
    }

    /**
     * Sets the decoratedType's set
     * @param leaderboards the {@link Leaderboard}s associated with the Event
     */
    public void setLeaderboards(List<Leaderboard> leaderboards){
        decoratedType.setLeaderboards(leaderboards);
    }

    /**
     * @return true or false based on if the decoratedType is trackable or not
     */
    public Boolean isAvailable() {
        return decoratedType.isAvailable();
    }

    /**
     * Sets the decoratedType's availability
     * @param available whether the Event is available to be entered
     */
    public void setAvailable(Boolean available){
        decoratedType.setAvailable(available);
    }

    /**
     * @return the time limit for the event, if applicable
     */
    public Integer getTimeLimit() { return decoratedType.getTimeLimit(); }

    /**
     * Sets the decoratedType's time limit
     * @param limit the time limit for the event
     */
    public void setTimeLimit(Integer limit) { decoratedType.setTimeLimit(limit); }
}