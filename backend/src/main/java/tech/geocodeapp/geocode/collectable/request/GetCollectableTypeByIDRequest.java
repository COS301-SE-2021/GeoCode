package tech.geocodeapp.geocode.collectable.request;

import java.util.UUID;

public class GetCollectableTypeByIDRequest {

    /**
     * The id of the collectable to be searched for
     */
    private UUID collectableTypeID;

    /**
     * Default constructor
     */
    public GetCollectableTypeByIDRequest() {

    }

    /**
     * Overloaded Constructor
     *
     * @param collectableID The collectableID to be searched for
     */
    public GetCollectableTypeByIDRequest( UUID collectableID ) {

        this.collectableTypeID = collectableID;
    }

    /**
     *  Gets the saved collectableID attribute
     *
     * @return the stored collectableID attribute
     */
    public UUID getCollectableTypeID() {

        return collectableTypeID;
    }

    /**
     * Sets the collectableID attribute to the specified value
     *
     * @param collectableTypeID the value the attribute should be set to
     */
    public void setCollectableTypeID( UUID collectableTypeID ) {

        this.collectableTypeID = collectableTypeID;
    }

}
