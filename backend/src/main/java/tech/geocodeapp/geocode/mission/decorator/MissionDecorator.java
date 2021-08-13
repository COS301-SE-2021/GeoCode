package tech.geocodeapp.geocode.mission.decorator;

import tech.geocodeapp.geocode.geocode.model.GeoPoint;

import java.util.UUID;

public abstract class MissionDecorator implements MissionComponent{
    //the decorated mission
    protected MissionComponent decoratedMission;

    public MissionDecorator(MissionComponent decoratedMission) {
        this.decoratedMission = decoratedMission;
    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public void setId(UUID id) {

    }

    @Override
    public Integer getAmount() {
        return null;
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
