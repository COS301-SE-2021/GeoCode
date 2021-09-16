package tech.geocodeapp.geocode.image.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

/**
 * GetImageRequest
 */
@Validated
public class GetImageRequest {

    private String fileName = null;

    public GetImageRequest(String fileName) {
        this.fileName = fileName;
    }

    public GetImageRequest fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    /**
     * Get fileName
     * @return fileName
     **/
    @Valid
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetImageRequest getImageDataRequest = (GetImageRequest) o;
        return Objects.equals(this.fileName, getImageDataRequest.fileName) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetImageRequest {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
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
