package tech.geocodeapp.geocode.collectable.response;


/**
 * A class to write a response to for a createCollectableRequest
 */
public class CreateCollectableResponse {
    boolean success;
    String message;
    CollectableResponse collectable;

    public CreateCollectableResponse() {
    }

    public CreateCollectableResponse(boolean success, String message, CollectableResponse collectable) {
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

    public CollectableResponse getCollectable() {
        return collectable;
    }

    public void setCollectable(CollectableResponse collectable) {
        this.collectable = collectable;
    }
}
