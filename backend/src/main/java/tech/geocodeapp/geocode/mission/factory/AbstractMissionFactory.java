package tech.geocodeapp.geocode.mission.factory;

import tech.geocodeapp.geocode.mission.decorator.MissionComponent;
import tech.geocodeapp.geocode.mission.model.Mission;

public abstract class AbstractMissionFactory {

    /**
     * @param mission the {@link Mission} to build the {@link MissionComponent}
     * @param missionComponent the {@link MissionComponent} to decorate
     * @return
     */
    public abstract MissionComponent decorateMission(Mission mission, MissionComponent missionComponent);
}
