package tech.geocodeapp.geocode.mission.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.geocodeapp.geocode.general.CheckNullRequestParameters;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.repository.MissionRepository;
import tech.geocodeapp.geocode.mission.request.GetMissionByIdRequest;
import tech.geocodeapp.geocode.mission.request.GetProgressRequest;
import tech.geocodeapp.geocode.mission.response.GetMissionByIdResponse;
import tech.geocodeapp.geocode.mission.response.GetProgressResponse;

import java.util.Optional;

@Service("MissionService")
public class MissionServiceImpl implements MissionService{
    private final MissionRepository missionRepo;
    private final CheckNullRequestParameters checkNullRequestParameters = new CheckNullRequestParameters();

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
        if(request == null){
            return new GetMissionByIdResponse(false, "The GetMissionByIdRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        Optional<Mission> optionalMission = missionRepo.findById(request.getMissionID());

        return optionalMission.map(mission -> new GetMissionByIdResponse(true, "Mission found", mission)).orElseGet(
                () -> new GetMissionByIdResponse(false, "Mission not found", null));
    }

    @Transactional
    public GetProgressResponse getProgress(GetProgressRequest request) throws NullRequestParameterException {
        return null;
    }
}
