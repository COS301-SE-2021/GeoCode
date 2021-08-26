package tech.geocodeapp.geocode.image.response;

import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.image.model.Image;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * GetImageResponse
 */
@Validated
public class GetImageResponse extends Response {

    private Image image = null;

    public GetImageResponse(boolean success, String message, Image image) {
        super(success, message);
        this.image = image;
    }

    public GetImageResponse image(Image image) {
        this.image = image;
        return this;
    }

    /**
     * Get image
     * @return image
     **/

    @Valid
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetImageResponse getImageBytesResponse = (GetImageResponse) o;
        return Objects.equals(this.image, getImageBytesResponse.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetImageResponse {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    image: ").append(toIndentedString(image)).append("\n");
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
