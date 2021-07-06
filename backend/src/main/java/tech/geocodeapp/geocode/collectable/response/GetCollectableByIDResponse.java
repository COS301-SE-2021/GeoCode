package tech.geocodeapp.geocode.collectable.response;

import tech.geocodeapp.geocode.collectable.model.Collectable;

public class GetCollectableByIDResponse {

    /**
     * The found collectable with the given collectableID
     */
    private Collectable collectable;

    /**
     * Default constructor
     */
    public GetCollectableByIDResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param collectable The collectable from the specified collectable
     */
    public GetCollectableByIDResponse( Collectable collectable ) {

        this.collectable = collectable;
    }

    /**
     *  Gets the saved collectable attribute
     *
     * @return the stored collectable attribute
     */
    public Collectable getCollectable() {

        return collectable;
    }

    /**
     * Sets the collectable attribute to the specified value
     *
     * @param collectable the value the attribute should be set to
     */
    public void setCollectable( Collectable collectable ) {

        this.collectable = collectable;
    }

}
