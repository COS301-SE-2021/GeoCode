package tech.geocodeapp.geocode.mission.service;

import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.mission.request.CreateMissionRequest;
import tech.geocodeapp.geocode.mission.request.GetMissionByIdRequest;
import tech.geocodeapp.geocode.mission.request.GetProgressRequest;
import tech.geocodeapp.geocode.mission.request.UpdateCompletionRequest;
import tech.geocodeapp.geocode.mission.response.CreateMissionResponse;
import tech.geocodeapp.geocode.mission.response.GetMissionByIdResponse;
import tech.geocodeapp.geocode.mission.response.GetProgressResponse;
import tech.geocodeapp.geocode.user.response.UpdateCompletionResponse;

/**
 * This interface is for the service for the Mission subsystem
 */
public interface MissionService {
    //U1.1 getMissionById
    GetMissionByIdResponse getMissionById(GetMissionByIdRequest request) throws NullRequestParameterException;

    //U1.2 getProgress
    GetProgressResponse getProgress(GetProgressRequest request) throws NullRequestParameterException;

    //U1.3 createMission
    CreateMissionResponse createMission(CreateMissionRequest request) throws NullRequestParameterException;

    //U1.4 updateCompletion
    UpdateCompletionResponse updateCompletion(UpdateCompletionRequest request) throws NullRequestParameterException;
}
