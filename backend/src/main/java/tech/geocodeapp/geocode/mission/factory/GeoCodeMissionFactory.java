package tech.geocodeapp.geocode.mission.factory;

import tech.geocodeapp.geocode.mission.decorator.GeoCodeMission;
import tech.geocodeapp.geocode.mission.decorator.MissionComponent;
import tech.geocodeapp.geocode.mission.model.Mission;

public class GeoCodeMissionFactory extends AbstractMissionFactory{
    /**
     * @param mission the {@link Mission} to build the {@link MissionComponent}
     * @param missionComponent the {@link MissionComponent} to decorate
     * @return the created {@link tech.geocodeapp.geocode.mission.decorator.GeoCodeMission}
     */
    @Override
    public MissionComponent decorateMission(Mission mission, MissionComponent missionComponent) {
        MissionComponent toReturn = new GeoCodeMission(missionComponent);
        toReturn.checkIfFinished();

        return toReturn;
    }
}
