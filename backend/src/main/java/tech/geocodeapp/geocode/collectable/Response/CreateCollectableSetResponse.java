package tech.geocodeapp.geocode.collectable.response;

import tech.geocodeapp.geocode.collectable.model.CollectableSet;

/**
 * A class to write a response to for a createCollectableSetRequest
 */
public class CreateCollectableSetResponse {
    boolean success;
    String message;
    CollectableSet collectableSet;

    public CreateCollectableSetResponse() {
    }

    public CreateCollectableSetResponse(boolean success, String message, CollectableSet collectableSet) {
        this.success = success;
        this.message = message;
        this.collectableSet = collectableSet;
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

    public CollectableSet getCollectableSet() {
        return collectableSet;
    }

    public void setCollectableSet(CollectableSet collectableSet) {
        this.collectableSet = collectableSet;
    }
}
