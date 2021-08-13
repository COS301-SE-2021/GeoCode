package tech.geocodeapp.geocode.mission.decorator;

import tech.geocodeapp.geocode.geocode.model.GeoPoint;

import java.util.UUID;

public class ConcreteMission implements MissionComponent{

    private UUID id;
    //stores the value required for finishing a mission when it is not a location
    private Integer amount;
    private GeoPoint location;
    //stores the value to compare to amount to track progress
    private Integer completion;

    public ConcreteMission() {
    }

    /**
     * get the identifying id of the mission
     * @return the id of the mission
     */
    @Override
    public UUID getId() {
        return id;
    }

    /**
     * set the mission id
     * @param id the id to set the mission id to
     */
    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * get the stored amount from the mission
     * @return the amount of the mission
     */
    @Override
    public Integer getAmount() {
        return amount;
    }

    /**
     * sets the mission's amount to a provided value
     * @param amount the amount to set the mission's amount to
     */
    @Override
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * the location the mission was last at or needs to go to
     * @return the last location of the mission
     */
    @Override
    public GeoPoint getLocation() {
        return location;
    }

    /**
     * sets the location of a mission to a provided value
     * @param location the location to set it to
     */
    @Override
    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    /**
     * returns the completion progress of a mission
     * @return the completion progress of the mission
     */
    @Override
    public Integer getCompletion() {
        return completion;
    }

    /**
     * sets the completion progress of a mission to a provided value
     * @param completion - the value to set the completion progress to
     */
    @Override
    public void setCompletion(Integer completion) {
        this.completion = completion;
    }

    /**
     * not used in concreteMission
     */
    @Override
    public void calculateDistance(GeoPoint newLocation) {
    }

    /**
     * set to false in concrete mission
     * @return false
     */
    @Override
    public boolean checkIfFinished() {
        return false;
    }
}
