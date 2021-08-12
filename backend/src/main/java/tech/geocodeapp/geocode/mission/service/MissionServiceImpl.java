package tech.geocodeapp.geocode.mission.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.mission.repository.MissionRepository;
import tech.geocodeapp.geocode.mission.request.GetMissionByIdRequest;
import tech.geocodeapp.geocode.mission.request.GetProgressRequest;
import tech.geocodeapp.geocode.mission.response.GetMissionByIdResponse;
import tech.geocodeapp.geocode.mission.response.GetProgressResponse;

@Service("MissionService")
public class MissionServiceImpl implements MissionService{
    private final MissionRepository missionRepo;

    public MissionServiceImpl(MissionRepository missionRepo) {
        this.missionRepo = missionRepo;
    }

    /**
     * Gets the Mission specified by the given ID
     * @param request GetMissionByIdRequest object
     * @return GetMissionByIdResponse object
     */
    @Transactional
    public GetMissionByIdResponse getMissionById(GetMissionByIdRequest request) throws NullRequestParameterException {
        return null;
    }

    @Transactional
    public GetProgressResponse getProgress(GetProgressRequest request) throws NullRequestParameterException {
        return null;
    }
}
