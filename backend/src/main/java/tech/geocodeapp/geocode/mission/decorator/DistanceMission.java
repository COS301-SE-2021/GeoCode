package tech.geocodeapp.geocode.mission.decorator;

public class DistanceMission extends MissionDecorator{

    public DistanceMission(MissionComponent decoratedMission) {
        super(decoratedMission);
    }
}
