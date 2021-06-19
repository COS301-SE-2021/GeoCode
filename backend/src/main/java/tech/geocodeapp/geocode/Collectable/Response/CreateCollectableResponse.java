package tech.geocodeapp.geocode.Collectable.Response;

import io.swagger.model.Collectable;

/**
 * A class to write a response to for a createCollectableRequest
 */
public class CreateCollectableResponse {
    boolean success;
    String message;
    Collectable collectable;

    public CreateCollectableResponse() {
    }

    public CreateCollectableResponse(boolean success, String message, Collectable collectable) {
        this.success = success;
        this.message = message;
        this.collectable = collectable;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collectable getCollectable() {
        return collectable;
    }

    public void setCollectable(Collectable collectable) {
        this.collectable = collectable;
    }
}
