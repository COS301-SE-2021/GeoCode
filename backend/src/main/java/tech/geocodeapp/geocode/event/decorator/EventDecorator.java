package tech.geocodeapp.geocode.event.decorator;

import tech.geocodeapp.geocode.event.model.UserEventStatus;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

import java.time.LocalDate;
import java.util.List;

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
    public java.util.UUID getID() {
        return decoratedType.getID();
    }

    /**
     * Sets the decoratedType's name variable
     * @param id the id of the Event
     */
    public void setID(java.util.UUID id) { decoratedType.setID(id); }

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
    public List<java.util.UUID> getGeocodeIDs() {
        return decoratedType.getGeocodeIDs();
    }

    /**
     * Sets the decoratedType's list of geocode IDs
     * @param ids the list of ids
     */
    public void setGeocodeIDs(List<java.util.UUID> ids) {
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

    /**
     * Function to record information in the status object when the user starts in an event
     * @param status The user's current status in an event
     */
    @Override
    public void handleEventStart(UserEventStatus status) { decoratedType.handleEventStart(status); }

    /**
     * Function to record information in the status object when the user completes a stage
     * @param stageNumber The index of the stage that was just completed
     * @param status The user's current status in an event
     */
    @Override
    public void handleStageCompletion(int stageNumber, UserEventStatus status) { decoratedType.handleStageCompletion(stageNumber, status); }

    /**
     * Function to record information in the status object when the user finishes an event
     * @param status The user's current status in an event
     */
    @Override
    public void handleEventEnd(UserEventStatus status) { decoratedType.handleEventEnd(status); }

    /**
     * Function to calculate the number of points a user should receive for finding the provided geocode.
     *
     * @param foundGeocode The geocode that was just found
     * @param stageNumber The index of the stage that was just completed
     * @param status The user's current status in an event
     *
     * @return The updated number of points they have earned after going through this decorator
     */
    @Override
    public int calculatePoints(GeoCode foundGeocode, int stageNumber, UserEventStatus status) {
        return decoratedType.calculatePoints(foundGeocode, stageNumber, status);
    }

    /**
     * @return the decoratedType's blocklyEvent
     */
    @Override
    public boolean isBlocklyEvent() {
        return decoratedType.isBlocklyEvent();
    }

    /**
     * Sets the decoratedType's blocklyEvent
     * @param blocklyEvent whether or not the event is a blockly event
     */
    @Override
    public void setBlocklyEvent(boolean blocklyEvent) {
        decoratedType.setBlocklyEvent(blocklyEvent);
    }

    /**
     * Gets the blocklyDetails of the decoratedType if applicable
     * @return the blocklyDetails or null if the event is not that type
     */
    @Override
    public String getBlocklyDetails() {
        return decoratedType.getBlocklyDetails();
    }

    /**
     * Sets the decoratedType's blocklyDetails
     * @param blocklyDetails the blockly details
     */
    @Override
    public void setBlocklyDetails(String blocklyDetails) {
        decoratedType.setBlocklyDetails(blocklyDetails);
    }

    /**
     * Gets the inputs of the decoratedType if applicable
     * @return the inputs or null if the event is not that type
     */
    @Override
    public List<String> getInputs() {
        return decoratedType.getInputs();
    }

    /**
     * Sets the decoratedType's inputs
     * @param inputs the inputs used
     */
    @Override
    public void setInputs(List<String> inputs) {
        decoratedType.setInputs(inputs);
    }

    /**
     * Gets the outputs of the decoratedType if applicable
     * @return the outputs or null if the event is not that type
     */
    @Override
    public List<String> getOutputs() {
        return decoratedType.getOutputs();
    }

    /**
     * Sets the decoratedType's outputs
     * @param outputs the expected outputs of the event
     */
    @Override
    public void setOutputs(List<String> outputs) {
        decoratedType.setOutputs(outputs);
    }
}
