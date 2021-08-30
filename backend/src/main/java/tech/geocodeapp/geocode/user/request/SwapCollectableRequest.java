package tech.geocodeapp.geocode.user.request;

import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.user.model.User;

public class SwapCollectableRequest {
    /*
    * The User to swap the Collectable for
     */
    private User user;

    /**
     * The collectable to be swapped in
     */
    private Collectable collectable;

    /**
     * The GeoCode that the Collectable is in
     */
    private GeoCode geocode;

    /**
     * Default constructor
     */
    public SwapCollectableRequest() {

    }

    /**
     * Overloaded Constructor
     * @param user The  User to swap the Collectable for
     * @param collectable The collectableID to be searched for
     * @param geocode The GeoCode
     */
    public SwapCollectableRequest(User user, Collectable collectable, GeoCode geocode) {
        this.user = user;
        this.collectable = collectable;
        this.geocode = geocode;
    }

    /**
     *  Gets the saved collectableID attribute
     *
     * @return the stored collectableID attribute
     */
    public Collectable getCollectable() {

        return collectable;
    }

    /**
     * Sets the collectableID attribute to the specified value
     *
     * @param collectable the value the attribute should be set to
     */
    public void setCollectable(Collectable collectable) {

        this.collectable = collectable;
    }

    public GeoCode getGeoCode() {
        return geocode;
    }

    public void setGeoCode(GeoCode geocode) {
        this.geocode = geocode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
