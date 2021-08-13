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
