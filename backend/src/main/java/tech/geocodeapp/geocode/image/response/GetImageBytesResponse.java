package tech.geocodeapp.geocode.image.response;

import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.image.model.Image;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * GetImageBytesResponse
 */
@Validated
public class GetImageBytesResponse extends Response {

    private byte[] bytes = null;

    public GetImageBytesResponse(boolean success, String message, byte[] bytes) {
        super(success, message);
        this.bytes = bytes;
    }

    public GetImageBytesResponse bytes(byte[] bytes) {
        this.bytes = bytes;
        return this;
    }

    /**
     * Get bytes
     * @return bytes
     **/
    @NotNull

    @Valid
    public byte[] getImageBytes() {
        return bytes;
    }

    public void setImageBytes(byte[] bytes) {
        this.bytes = bytes;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetImageBytesResponse getImageBytesResponse = (GetImageBytesResponse) o;
        return Objects.equals(this.bytes, getImageBytesResponse.bytes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bytes, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetImageBytesResponse {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    bytes: ").append(toIndentedString(bytes)).append("\n");
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
