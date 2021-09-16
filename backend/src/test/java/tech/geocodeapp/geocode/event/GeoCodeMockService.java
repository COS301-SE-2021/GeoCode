package tech.geocodeapp.geocode.event;

import tech.geocodeapp.geocode.collectable.decorator.CollectableTypeComponent;
import tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.repository.GeoCodeRepository;
import tech.geocodeapp.geocode.geocode.request.*;
import tech.geocodeapp.geocode.geocode.response.*;
import tech.geocodeapp.geocode.geocode.service.GeoCodeService;

import java.util.ArrayList;
import java.util.List;

public class GeoCodeMockService implements GeoCodeService {

    private final GeoCodeRepository geocodeRepo;

    public GeoCodeMockService(GeoCodeRepository geocodeRepo) {
        this.geocodeRepo = geocodeRepo;
    }

    @Override
    public CreateGeoCodeResponse createGeoCode(CreateGeoCodeRequest request) throws InvalidRequestException {
        var geocode = new GeoCode();
        geocode.setId(request.getId());
        geocode.setDifficulty(request.getDifficulty());

        /* mock the QR code so can call getCollectablesInGeoCodeByQRCode */
        geocode.setQrCode(request.getDescription());

        geocode.setEventID(request.getEventComponent().getID());

        geocodeRepo.save(geocode);

        return new CreateGeoCodeResponse(true, "CreateGeoCode", geocode);
    }

    /**
     * Update a stored GeoCode
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public UpdateGeoCodeResponse updateGeoCode( UpdateGeoCodeRequest request ) throws InvalidRequestException {

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
        /* Retrieve all the stored GeoCodes from the repository */
        List< GeoCode > temp = new ArrayList<>( geocodeRepo.findGeoCode() );

        /* Go through each GeoCode found and hide the sensitive data */
        for ( GeoCode geoCode : temp ) {

            geoCode.setHints( null );
            geoCode.setQrCode( null );
            geoCode.setCollectables( null );
        }

        /* Set the response to the masked GeoCodes and return it */
        return new GetGeoCodesResponse( temp );
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
    public GetGeoCodesByLocationResponse getGeoCodesByLocation(GetGeoCodesByLocationRequest request) throws InvalidRequestException {
        return null;
    }

    @Override
    public GetCollectablesInGeoCodeByLocationResponse getCollectablesInGeoCodeByLocation(GetCollectablesInGeoCodeByLocationRequest request) throws InvalidRequestException {
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

    /**
     * Determines what type of collectable to create
     * <p>
     * NOTE: a collectable of Type Rarity is a user Trackable and will not be considered
     *
     * @param items
     */
    @Override
    public CollectableTypeComponent calculateCollectableType( List< CollectableTypeComponent > items ) {

        return null;
    }

}