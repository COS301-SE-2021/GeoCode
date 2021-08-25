package tech.geocodeapp.geocode.image.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.general.response.Response;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateImageResponse
 */
@Validated
public class CreateImageResponse extends Response {

    @JsonProperty("imageID")
    private UUID imageID = null;

    public CreateImageResponse() {
    }

    public CreateImageResponse(boolean success, String message, UUID imageID) {
        super(success, message);
        this.imageID = imageID;
    }

    public CreateImageResponse imageID(UUID imageID) {
        this.imageID = imageID;
        return this;
    }

    /**
     * Get imageID
     * @return imageID
     **/
    @Schema(example = "054463f2-2f7c-4864-8130-68e5aa79ee7f", required = true, description = "")
    @NotNull

    @Valid
    public UUID getImageID() {
        return imageID;
    }

    public void setImageID(UUID imageID) {
        this.imageID = imageID;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateImageResponse createImageResponse = (CreateImageResponse) o;
        return Objects.equals(this.imageID, createImageResponse.imageID) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageID, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateImageResponse {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    imageID: ").append(toIndentedString(imageID)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
