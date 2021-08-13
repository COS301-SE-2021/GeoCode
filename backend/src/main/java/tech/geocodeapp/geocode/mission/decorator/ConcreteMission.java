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

    @Override
    public void setAmount(Integer amount) {

    }

    @Override
    public GeoPoint getLocation() {
        return null;
    }

    @Override
    public void setLocation(GeoPoint location) {

    }

    @Override
    public Integer getCompletion() {
        return null;
    }

    @Override
    public void setCompletion(Integer completion) {

    }

    @Override
    public void calculateDistance() {

    }

    @Override
    public boolean checkIfFinished() {
        return false;
    }
}
