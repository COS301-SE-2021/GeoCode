package tech.geocodeapp.geocode.image.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

/**
 * GetImageDataRequest
 */
@Validated
public class GetImageBytesRequest {

    private UUID imageID = null;

    public GetImageBytesRequest(UUID imageID) {
        this.imageID = imageID;
    }

    public GetImageBytesRequest imageID(UUID imageID) {
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
        GetImageBytesRequest getImageDataRequest = (GetImageBytesRequest) o;
        return Objects.equals(this.imageID, getImageDataRequest.imageID) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageID, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetImageDataRequest {\n");
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
