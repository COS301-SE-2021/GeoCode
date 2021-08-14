package tech.geocodeapp.geocode.mission.factory;

import tech.geocodeapp.geocode.mission.decorator.ConcreteMission;
import tech.geocodeapp.geocode.mission.decorator.MissionComponent;
import tech.geocodeapp.geocode.mission.model.Mission;

public class ConcreteMissionFactory extends AbstractMissionFactory{


    /**
     * sets all of the shared variables of a mission
     * @param mission the {@link Mission} to build the {@link MissionComponent}
     * @param missionComponent the {@link MissionComponent} to decorate
     * @return
     */
    @Override
    public MissionComponent decorateMission(Mission mission, MissionComponent missionComponent) {
        MissionComponent toReturn = new ConcreteMission();
        toReturn.setId(mission.getId());
        toReturn.setAmount(mission.getAmount());
        toReturn.setLocation(mission.getLocation());
        toReturn.setCompletion(mission.getCompletion());

        return toReturn;
    }
}
