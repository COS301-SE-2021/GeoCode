package tech.geocodeapp.geocode.geocode.response;

import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.geocode.model.GeoCode;

public class GetGeoCodeByIDResponse extends Response {
    /**
     * The wanted GeoCode object
     */
    private GeoCode geoCode;

    public GetGeoCodeByIDResponse(boolean success, String message, GeoCode geoCode) {
        super(success, message);
        this.geoCode = geoCode;
    }

    public GeoCode getGeoCode() {
        return geoCode;
    }

    public void setGeoCode(GeoCode geoCode) {
        this.geoCode = geoCode;
    }
}
