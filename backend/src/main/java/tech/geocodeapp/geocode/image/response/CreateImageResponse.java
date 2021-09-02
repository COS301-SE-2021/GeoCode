package tech.geocodeapp.geocode.image.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.general.response.Response;

import javax.validation.Valid;

/**
 * CreateImageResponse
 */
@Validated
public class CreateImageResponse extends Response {

    @JsonProperty("fileName")
    private String fileName = null;

    public CreateImageResponse(boolean success, String message, String fileName) {
        super(success, message);
        this.fileName = fileName;
    }

    public CreateImageResponse fileName(String fileName) {
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
        CreateImageResponse createImageResponse = (CreateImageResponse) o;
        return Objects.equals(this.fileName, createImageResponse.fileName) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateImageResponse {\n");
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
