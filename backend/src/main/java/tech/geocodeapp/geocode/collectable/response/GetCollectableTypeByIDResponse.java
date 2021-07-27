package tech.geocodeapp.geocode.collectable.response;

import tech.geocodeapp.geocode.collectable.model.CollectableType;

public class GetCollectableTypeByIDResponse {

    /**
     * The found collectable with the given collectableID
     */
    private CollectableType collectableType;

    /**
     * Default constructor
     */
    public GetCollectableTypeByIDResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param collectableType The collectable from the specified collectable
     */
    public GetCollectableTypeByIDResponse( CollectableType collectableType ) {

        this.collectableType = collectableType;
    }

    /**
     *  Gets the saved collectable attribute
     *
     * @return the stored collectable attribute
     */
    public CollectableType getCollectableType() {

        return collectableType;
    }

    /**
     * Sets the collectable attribute to the specified value
     *
     * @param collectableType the value the attribute should be set to
     */
    public void setCollectableType( CollectableType collectableType ) {

        this.collectableType = collectableType;
    }

}
