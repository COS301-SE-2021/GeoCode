package tech.geocodeapp.geocode.user.request;

import java.util.UUID;

public class SwapCollectableRequest {

    /**
     * The id of the collectable to be searched for
     */
    private UUID collectableID;

    /**
     * Default constructor
     */
    public SwapCollectableRequest() {

    }

    /**
     * Overloaded Constructor
     *
     * @param collectableID The collectableID to be searched for
     */
    public SwapCollectableRequest( UUID collectableID ) {

        this.collectableID = collectableID;
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
}
