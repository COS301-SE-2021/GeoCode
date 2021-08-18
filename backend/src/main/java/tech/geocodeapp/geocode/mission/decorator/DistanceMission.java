package tech.geocodeapp.geocode.mission.decorator;

import tech.geocodeapp.geocode.geocode.model.GeoPoint;

public class DistanceMission extends MissionDecorator{

    public DistanceMission(MissionComponent decoratedMission) {
        super(decoratedMission);
    }

    /**
     * calculate the distance between the new location of a linked collectable and the previous location kept by
     * the mission
     * @param newLocation the location to compare
     */
    @Override
    public void calculateDistance(GeoPoint newLocation) {
        double distance = decoratedMission.getLocation().distanceTo(newLocation);
        decoratedMission.setAmount(decoratedMission.getAmount() + ((int) distance));
    }

    /**
     * checks if the mission is complete
     * @return the value returned by the .equals method
     */
    @Override
    public boolean checkIfFinished() {
        finished = decoratedMission.getAmount().equals(decoratedMission.getCompletion());
        return finished;
    }
}
