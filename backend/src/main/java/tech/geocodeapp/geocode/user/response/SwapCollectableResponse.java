package tech.geocodeapp.geocode.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.general.response.Response;

public class SwapCollectableResponse extends Response {

    /**
     * The returned Collectable that was in the GeoCode
     */
    @JsonProperty("collectable")
    private Collectable collectable;

    /**
     * Overloaded Constructor
     *
     * @param collectable The collectable that is swapped out of the GeoCode
     */
    public SwapCollectableResponse( boolean success, String message, Collectable collectable ) {
        super(success, message);
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
