package tech.geocodeapp.geocode.mission.decorator;

import tech.geocodeapp.geocode.geocode.model.GeoPoint;

public class GeoCodeMission extends MissionDecorator{

    public GeoCodeMission(MissionComponent decoratedMission) {
        super(decoratedMission);
    }

    /**
     * checks if the provided location is the same as the missions location and sets completion to 100 if it is
     * @param newLocation the location to compare
     */
    @Override
    public void checkLocation(GeoPoint newLocation) {
        if(decoratedMission.getLocation().equals(newLocation)) {
            decoratedMission.setCompletion(100);
        }
    }
}
