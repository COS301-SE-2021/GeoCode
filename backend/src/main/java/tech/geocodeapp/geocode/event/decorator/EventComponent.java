package tech.geocodeapp.geocode.event.decorator;

import tech.geocodeapp.geocode.event.blockly.Block;
import tech.geocodeapp.geocode.event.model.UserEventStatus;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

import java.time.LocalDate;
import java.util.List;

public interface EventComponent {

    /**
     * Getters and setters for all variables that the concrete component includes
     */

    java.util.UUID getID();

    void setID(java.util.UUID id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    GeoPoint getLocation();

    void setLocation(GeoPoint location);

    List<java.util.UUID> getGeocodeIDs();

    void setGeocodeIDs(List<java.util.UUID> geocodeIDs);

    LocalDate getBeginDate();

    void setBeginDate(LocalDate date);

    LocalDate getEndDate();

    void setEndDate(LocalDate date);

    List<Leaderboard> getLeaderboards();

    void setLeaderboards(List<Leaderboard> leaderboards);

    Boolean isAvailable();

    void setAvailable(Boolean available);

    /**
     * Getter for checking if the event is a normal event or a blockly one
     */
    boolean isBlocklyEvent();

    /**
     * Setter used to set the blocklyEvent variable
     */
    void setBlocklyEvent(boolean blocklyEvent);

    /**
     * Getter for blockly events used to get the blocklyDetails variable from {@link BlocklyEventDecorator}
     * @return The blocks for the event
     */
    Block[] getBlocks();

    /**
     * Setter for blockly events used to set the blocklyDetails variable from {@link BlocklyEventDecorator}
     * @param blocks Array of blocks for the event
     */
    void setBlocks(Block[] blocks);

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
    int calculatePoints(GeoCode foundGeocode, int stageNumber, UserEventStatus status);

    /**
     * Getter for blockly events used to get the inputs variable from {@link BlocklyEventDecorator}
     * @return The input values for each test case
     */
    String[][] getInputs();

    /**
     * Setter for blockly events used to set the inputs variable from {@link BlocklyEventDecorator}
     * @param inputs The input values for each test case
     */
    void setInputs(String[][] inputs);

    /**
     * Getter for blockly events used to get the outputs variable from {@link BlocklyEventDecorator}
     * @return The output for each test case
     */
    String[] getOutputs();

    /**
     * Setter for blockly events used to set the outputs variable from {@link BlocklyEventDecorator}
     * @param outputs The output for each test case
     */
    void setOutputs(String[] outputs);

    /**
     * Sets the problem description if applicable
     * @param problemDescription The problem description
     */
    void setProblemDescription(String problemDescription);

    /**
     * Gets the problem description if applicable
     * @return The problem description
     */
    String getProblemDescription();
}
