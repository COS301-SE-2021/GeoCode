package tech.geocodeapp.geocode.geocode.request;

import java.util.UUID;

public class GetGeoCodeByIDRequest {
    /**
     * The UUID of the GeoCode to get
     */
    private UUID geoCodeID;

    public GetGeoCodeByIDRequest(UUID geocodeID) {
        this.geoCodeID = geocodeID;
    }

    public UUID getGeoCodeID() {
        return geoCodeID;
    }

    public void setGeoCodeID(UUID geoCodeID) {
        this.geoCodeID = geoCodeID;
    }
}
