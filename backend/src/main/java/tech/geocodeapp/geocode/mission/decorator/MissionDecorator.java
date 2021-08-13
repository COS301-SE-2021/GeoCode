package tech.geocodeapp.geocode.mission.decorator;

import tech.geocodeapp.geocode.geocode.model.GeoPoint;

import java.util.UUID;

public abstract class MissionDecorator implements MissionComponent{
    //the decorated mission
    protected MissionComponent decoratedMission;

    public MissionDecorator(MissionComponent decoratedMission) {
        this.decoratedMission = decoratedMission;
    }

    /**
     * @return the value returned by the decoratedMission's getId() method
     */
    @Override
    public UUID getId() {
        return decoratedMission.getId();
    }

    /**
     * Sets the decoratedMission's id
     * @param id the id of the mission
     */
    @Override
    public void setId(UUID id) {
        decoratedMission.setId(id);
    }

    /**
     * @return the value returned by the decoratedMission's getAmount() method
     */
    @Override
    public Integer getAmount() {
        return decoratedMission.getAmount();
    }


    /**
     * sets the decoratedMission's amount
     * @param amount the amount of the mission
     */
    @Override
    public void setAmount(Integer amount) {
        decoratedMission.setAmount(amount);
    }

    /**
     * @return the value returned by the decoratedMission's getLocation() method
     */
    @Override
    public GeoPoint getLocation() {
        return decoratedMission.getLocation();
    }

    /**
     * sets the location of the decoratedMission
     * @param location the location of the mission
     */
    @Override
    public void setLocation(GeoPoint location) {
        decoratedMission.setLocation(location);
    }

    /**
     * @return the value of the decoratedMission's getCompletion() method
     */
    @Override
    public Integer getCompletion() {
        return decoratedMission.getCompletion();
    }

    /**
     * sets the completion of the decoratedMission
     * @param completion the completion of the mission
     */
    @Override
    public void setCompletion(Integer completion) {
        decoratedMission.setCompletion(completion);
    }

    /**
     * calls the decoratedMission's calculateDistance()
     */
    @Override
    public void calculateDistance(GeoPoint newLocation) {
        decoratedMission.calculateDistance(newLocation);
    }

    /**
     * @return the value of the decoratedMission's checkIfFinished()
     */
    @Override
    public boolean checkIfFinished() {
        return decoratedMission.checkIfFinished();
    }
}
