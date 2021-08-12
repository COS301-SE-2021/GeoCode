package tech.geocodeapp.geocode.mission.service;

import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.mission.request.GetMissionByIdRequest;
import tech.geocodeapp.geocode.mission.request.GetProgressRequest;
import tech.geocodeapp.geocode.mission.response.GetMissionByIdResponse;
import tech.geocodeapp.geocode.mission.response.GetProgressResponse;

/**
 * This interface is for the service for the Mission subsystem
 */
public interface MissionService {
    //U1.1 getMissionById
    GetMissionByIdResponse getMissionById(GetMissionByIdRequest request) throws NullRequestParameterException;

    //U1.2 getProgress
    GetProgressResponse getProgress(GetProgressRequest request) throws NullRequestParameterException;
}