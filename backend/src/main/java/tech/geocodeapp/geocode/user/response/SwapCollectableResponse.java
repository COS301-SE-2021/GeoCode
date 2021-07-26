package tech.geocodeapp.geocode.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.collectable.model.Collectable;

public class SwapCollectableResponse {
    @JsonProperty("success")
    private Boolean success = null;

    @JsonProperty("message")
    private String message = null;

    /**
     * The found collectable with the given collectableID
     */
    private Collectable collectable;

    /**
     * Default constructor
     */
    public SwapCollectableResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param collectable The collectable from the specified collectable
     */
    public SwapCollectableResponse( boolean success, String message, Collectable collectable ) {
        this.success = success;
        this.message = message;
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
