package tech.geocodeapp.geocode.Collectable.Response;

import io.swagger.model.CollectableType;

/**
 * A class to write a response to for a createCollectableTypeRequest
 */
public class CreateCollectableTypeResponse {
    boolean success;
    String message;
    CollectableType collectableType;

    public CreateCollectableTypeResponse() {
    }

    public CreateCollectableTypeResponse(boolean success, String message, CollectableType collectableType) {
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

    public CollectableType getCollectableType() {
        return collectableType;
    }

    public void setCollectableType(CollectableType collectableType) {
        this.collectableType = collectableType;
    }
}
