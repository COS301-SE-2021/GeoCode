package tech.geocodeapp.geocode.mission.factory;

import tech.geocodeapp.geocode.mission.decorator.MissionComponent;
import tech.geocodeapp.geocode.mission.decorator.SwapMission;
import tech.geocodeapp.geocode.mission.model.Mission;

public class SwapMissionFactory extends AbstractMissionFactory{

    /**
     * @param mission the {@link Mission} to build the {@link MissionComponent}
     * @param missionComponent the {@link MissionComponent} to decorate
     * @return the created {@link tech.geocodeapp.geocode.mission.decorator.SwapMission}
     */
    @Override
    public MissionComponent decorateMission(Mission mission, MissionComponent missionComponent) {
        MissionComponent toReturn = new SwapMission(missionComponent);
        toReturn.checkIfFinished();

        return toReturn;
    }
}
