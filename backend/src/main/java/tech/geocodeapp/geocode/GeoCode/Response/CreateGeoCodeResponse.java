package tech.geocodeapp.geocode.GeoCode.Response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * CreateGeoCodeResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-19T15:57:07.487Z[GMT]")


public class CreateGeoCodeResponse   {
  @JsonProperty("isSuccess")
  private Boolean isSuccess = null;

  public CreateGeoCodeResponse isSuccess(Boolean isSuccess) {
    this.isSuccess = isSuccess;
    return this;
  }

  /**
   * Get isSuccess
   * @return isSuccess
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Boolean isIsSuccess() {
    return isSuccess;
  }

  public void setIsSuccess(Boolean isSuccess) {
    this.isSuccess = isSuccess;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateGeoCodeResponse createGeoCodeResponse = (CreateGeoCodeResponse) o;
    return Objects.equals(this.isSuccess, createGeoCodeResponse.isSuccess);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isSuccess);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateGeoCodeResponse {\n");
    
    sb.append("    isSuccess: ").append(toIndentedString(isSuccess)).append("\n");
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
