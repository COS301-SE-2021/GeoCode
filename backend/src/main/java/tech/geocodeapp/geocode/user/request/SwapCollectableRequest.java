package tech.geocodeapp.geocode.user.request;

import java.util.UUID;

public class SwapCollectableRequest {
    /*
    * The id of the User to swap the Collectable for
     */
    private UUID userID;

    /**
     * The id of the collectable to be swapped in
     */
    private UUID collectableID;

    /**
     * The UUID of the GeoCode
     */
    private UUID geoCodeID;

    /**
     * Default constructor
     */
    public SwapCollectableRequest() {

    }

    /**
     * Overloaded Constructor
     * @param userID The id of the User to swap the Collectable for
     * @param collectableID The collectableID to be searched for
     * @param geoCodeID The UUID of the GeoCode
     */
    public SwapCollectableRequest(UUID userID, UUID collectableID, UUID geoCodeID) {
        this.userID = userID;
        this.collectableID = collectableID;
        this.geoCodeID = geoCodeID;
    }

    /**
     *  Gets the saved collectableID attribute
     *
     * @return the stored collectableID attribute
     */
    public UUID getCollectableID() {

        return collectableID;
    }

    /**
     * Sets the collectableID attribute to the specified value
     *
     * @param collectableID the value the attribute should be set to
     */
    public void setCollectableID( UUID collectableID ) {

        this.collectableID = collectableID;
    }

    public UUID getGeoCodeID() {
        return geoCodeID;
    }

    public void setGeoCodeID(UUID geoCodeID) {
        this.geoCodeID = geoCodeID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }
}
