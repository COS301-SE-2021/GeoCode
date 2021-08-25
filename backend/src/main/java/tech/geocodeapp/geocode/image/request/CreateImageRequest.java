package tech.geocodeapp.geocode.image.request;

import java.io.InputStream;
import java.util.Objects;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateImageRequest
 */
@Validated
public class CreateImageRequest {

    private InputStream imageByteStream = null;

    public CreateImageRequest(InputStream imageByteStream) {
        this.imageByteStream = imageByteStream;
    }

    public CreateImageRequest imageByteStream(InputStream imageByteStream) {
        this.imageByteStream = imageByteStream;
        return this;
    }

    /**
     * Get imageByteStream
     * @return imageByteStream
     **/
    @NotNull

    @Valid
    public InputStream getImageByteStream() {
        return imageByteStream;
    }

    public void setImageByteStream(InputStream imageByteStream) {
        this.imageByteStream = imageByteStream;
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
        return Objects.equals(this.imageByteStream, createImageRequest.imageByteStream);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageByteStream, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateImageRequest {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    imageByteStream: ").append(toIndentedString(imageByteStream)).append("\n");
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
