package tech.geocodeapp.geocode.image.request;

import java.awt.image.BufferedImage;
import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateImageRequest
 */
@Validated
public class CreateImageRequest {

    private BufferedImage imageData = null;

    public CreateImageRequest(BufferedImage imageData) {
        this.imageData = imageData;
    }

    public CreateImageRequest imageData(BufferedImage imageData) {
        this.imageData = imageData;
        return this;
    }

    /**
     * Get imageData
     * @return imageData
     **/
    @NotNull

    @Valid
    public BufferedImage getImageData() {
        return imageData;
    }

    public void setImageData(BufferedImage imageData) {
        this.imageData = imageData;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateImageRequest createImageRequest = (CreateImageRequest) o;
        return Objects.equals(this.imageData, createImageRequest.imageData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageData, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateImageRequest {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    imageData: ").append(toIndentedString(imageData)).append("\n");
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
