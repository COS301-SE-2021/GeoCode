package tech.geocodeapp.geocode.mission.service;

import tech.geocodeapp.geocode.mission.repository.MissionRepository;

public class MissionServiceImpl implements MissionService{
    private final MissionRepository missionRepo;

    public MissionServiceImpl(MissionRepository missionRepo) {
        this.missionRepo = missionRepo;
    }
}
