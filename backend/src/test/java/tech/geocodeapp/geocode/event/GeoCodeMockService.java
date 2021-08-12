package tech.geocodeapp.geocode.event;

import tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.repository.GeoCodeRepository;
import tech.geocodeapp.geocode.geocode.request.*;
import tech.geocodeapp.geocode.geocode.response.*;
import tech.geocodeapp.geocode.geocode.service.GeoCodeService;

public class GeoCodeMockService implements GeoCodeService {

    private final GeoCodeRepository geocodeRepo;

    public GeoCodeMockService(GeoCodeRepository geocodeRepo) {
        this.geocodeRepo = geocodeRepo;
    }

    @Override
    public CreateGeoCodeResponse createGeoCode(CreateGeoCodeRequest request) throws InvalidRequestException {
        return null;
    }

    @Override
    public GetGeoCodeResponse getGeoCode(GetGeoCodeRequest request) throws InvalidRequestException {
        var temp = geocodeRepo.findById(request.getGeoCodeID());
        if ( temp.isEmpty() ) {
            return new GetGeoCodeResponse( null );
        } else {
            return new GetGeoCodeResponse( temp.get() );
        }
    }

    @Override
    public GetGeoCodesResponse getAllGeoCodes() {
        return null;
    }

    @Override
    public GetCollectablesResponse getCollectables(GetCollectablesRequest request) throws InvalidRequestException {
        return null;
    }

    @Override
    public GetGeoCodesByDifficultyResponse getGeoCodesByDifficulty(GetGeoCodesByDifficultyRequest request) throws InvalidRequestException {
        return null;
    }

    @Override
    public GetGeoCodesByDifficultyListResponse getGeoCodesByDifficultyList(GetGeoCodesByDifficultyListRequest request) throws InvalidRequestException {
        return null;
    }

    @Override
    public GetHintsResponse getHints(GetHintsRequest request) throws InvalidRequestException {
        return null;
    }

    @Override
    public GetGeoCodeByQRCodeResponse getGeoCodeByQRCode(GetGeoCodeByQRCodeRequest request) throws InvalidRequestException {
        return null;
    }

    @Override
    public GetCollectablesInGeoCodeByQRCodeResponse getCollectablesInGeoCodeByQRCode(GetCollectablesInGeoCodeByQRCodeRequest request) throws InvalidRequestException {
        return null;
    }

    @Override
    public GetGeoCodeByLocationResponse getGeoCodesByLocation(GetGeoCodeByLocationRequest request) throws InvalidRequestException {
        return null;
    }

    @Override
    public GetCollectablesInGeoCodesByLocationResponse getCollectablesInGeoCodesByLocation(GetCollectablesInGeoCodesByLocationRequest request) throws InvalidRequestException {
        return null;
    }

    @Override
    public SwapCollectablesResponse swapCollectables(SwapCollectablesRequest request) throws InvalidRequestException {
        return null;
    }

    @Override
    public UpdateAvailabilityResponse updateAvailability(UpdateAvailabilityRequest request) throws InvalidRequestException {
        return null;
    }

    @Override
    public void saveGeoCode(GeoCode geocode) {

    }
}