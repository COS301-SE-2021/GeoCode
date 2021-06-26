package tech.geocodeapp.geocode.Collectable.Response;

import tech.geocodeapp.geocode.Collectable.Decorator.CollectableTypeComponent;

/**
 * A class to write a response to for a createCollectableTypeRequest
 */
public class CreateCollectableTypeResponse {
    boolean success;
    String message;
    CollectableTypeComponent collectableType;

    public CreateCollectableTypeResponse() {
    }

    public CreateCollectableTypeResponse(boolean success, String message, CollectableTypeComponent collectableType) {
        this.success = success;
        this.message = message;
        this.collectableType = collectableType;
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

    public CollectableTypeComponent getCollectableType() {
        return collectableType;
    }

    public void setCollectableType(CollectableTypeComponent collectableType) {
        this.collectableType = collectableType;
    }
}
