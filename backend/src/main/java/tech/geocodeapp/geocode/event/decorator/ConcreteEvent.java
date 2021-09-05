package tech.geocodeapp.geocode.event.decorator;

import tech.geocodeapp.geocode.event.model.UserEventStatus;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

import java.time.LocalDate;
import java.util.List;

public class ConcreteEvent implements EventComponent {

    private java.util.UUID id;
    private String name;
    private String description;
    private GeoPoint location;
    private List<java.util.UUID> geocodeIDs;
    private LocalDate beginDate;
    private LocalDate endDate;
    private List<Leaderboard> leaderboards;
    private Boolean available;
    private boolean blocklyEvent;

    public ConcreteEvent() { }

    @Override
    public java.util.UUID getID() { return id; }

    @Override
    public void setID(java.util.UUID id) { this.id = id; }

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
    public List<java.util.UUID> getGeocodeIDs() { return geocodeIDs; }

    @Override
    public void setGeocodeIDs(List<java.util.UUID> geocodeIDs) { this.geocodeIDs = geocodeIDs; }

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

    @Override
    public boolean isBlocklyEvent() {
        return blocklyEvent;
    }

    @Override
    public void setBlocklyEvent(boolean blocklyEvent) {
        this.blocklyEvent = blocklyEvent;
    }

    /**
     * @return null because blocklyDetails are not contained in the concreteComponent
     */
    @Override
    public String getBlocklyDetails() {
        return null;
    }

    /**
     * Do nothing because the blocklyDetails are not contained in the concreteComponent
     * @param blocklyDetails unused
     */
    @Override
    public void setBlocklyDetails(String blocklyDetails) {

    }

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

    /**
     * Do nothing in a concrete event
     */
    @Override
    public void handleEventStart(UserEventStatus status) { }

    /**
     * Do nothing in a concrete event
     * @param stageNumber The index of the stage that was just completed
     * @param status The user's current status in an event
     */
    @Override
    public void handleStageCompletion(int stageNumber, UserEventStatus status) { }

    /**
     * Do nothing in a concrete event
     * @param status The user's current status in an event
     */
    @Override
    public void handleEventEnd(UserEventStatus status) { }

    /**
     * Function to calculate the number of points a user should receive for finding the provided geocode.
     *
     * @param foundGeocode The index of the stage that was just completed
     * @param stageNumber The index of the stage that was just completed
     * @param status The user's current status in an event
     *
     * @return The number of points that the geocode earns the user
     */
    @Override
    public int calculatePoints(GeoCode foundGeocode, int stageNumber, UserEventStatus status) {
        switch(foundGeocode.getDifficulty()){
            case EASY: return 10;
            case MEDIUM: return 20;
            case HARD: return 30;
            case INSANE: return 40;
            default: return 0;
        }
    }

    /**
     * @return null because the inputs is not contained in the concreteComponent
     */
    @Override
    public List<String> getInputs() {
        return null;
    }

    /**
     * Do nothing in a concrete event
     * @param inputs unused
     */
    @Override
    public void setInputs(List<String> inputs) {

    }

    /**
     * @return null because the outputs is not contained in the concreteComponent
     */
    @Override
    public List<String> getOutputs() {
        return null;
    }

    /**
     * Do nothing in a concrete event
     * @param outputs unused
     */
    @Override
    public void setOutputs(List<String> outputs) {

    }
}
