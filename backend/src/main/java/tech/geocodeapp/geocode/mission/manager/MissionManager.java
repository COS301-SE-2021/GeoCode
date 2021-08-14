package tech.geocodeapp.geocode.mission.manager;

import tech.geocodeapp.geocode.mission.decorator.MissionComponent;
import tech.geocodeapp.geocode.mission.factory.*;
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.model.MissionType;

public class MissionManager {


    public MissionComponent buildMission(Mission mission) {
        MissionComponent missionComponent;
        AbstractMissionFactory factory = new ConcreteMissionFactory();

        //create the ConcreteMission
        missionComponent = factory.decorateMission(mission, null);

        //check what type of mission it is
        if(mission.getType().equals(MissionType.DISTANCE)) {
            factory = new DistanceMissionFactory();
            missionComponent = factory.decorateMission(mission, missionComponent);
        }else if(mission.getType().equals(MissionType.SWAP)) {
            factory = new SwapMissionFactory();
            missionComponent = factory.decorateMission(mission, missionComponent);
        }else if(mission.getType().equals(MissionType.GEOCODE)) {
            factory = new GeoCodeMissionFactory();
            missionComponent = factory.decorateMission(mission, missionComponent);
        }
        //ToDO selection for random mission type

        //return the created MissionComponent
        return missionComponent;
    }
}
