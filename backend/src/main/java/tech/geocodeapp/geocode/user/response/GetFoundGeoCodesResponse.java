package tech.geocodeapp.geocode.user.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetFoundGeoCodesResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-24T10:49:36.244Z[GMT]")


public class GetFoundGeoCodesResponse   {
  @JsonProperty("success")
  private Boolean success = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("geocodeIDs")
  @Valid
  private List<UUID> geocodeIDs = new ArrayList<UUID>();

  public GetFoundGeoCodesResponse(boolean success, String message, List<UUID> geocodeIDs) {
    this.success = success;
    this.message = message;
    this.geocodeIDs = geocodeIDs;
  }

  public GetFoundGeoCodesResponse success(Boolean success) {
    this.success = success;
    return this;
  }

  /**
   * Get success
   * @return success
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Boolean isSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public GetFoundGeoCodesResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   **/
  @Schema(example = "The IDs of the User's found GeoCodes was successfully returned", required = true, description = "")
      @NotNull

    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public GetFoundGeoCodesResponse geocodeIDs(List<UUID> geocodeIDs) {
    this.geocodeIDs = geocodeIDs;
    return this;
  }

  public GetFoundGeoCodesResponse addGeocodeIDsItem(UUID geocodeIDsItem) {
    this.geocodeIDs.add(geocodeIDsItem);
    return this;
  }

  /**
   * Get geocodeIDs
   * @return geocodeIDs
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<UUID> getGeocodeIDs() {
    return geocodeIDs;
  }

  public void setGeocodeIDs(List<UUID> geocodeIDs) {
    this.geocodeIDs = geocodeIDs;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetFoundGeoCodesResponse getFoundGeoCodesResponse = (GetFoundGeoCodesResponse) o;
    return Objects.equals(this.success, getFoundGeoCodesResponse.success) &&
        Objects.equals(this.message, getFoundGeoCodesResponse.message) &&
        Objects.equals(this.geocodeIDs, getFoundGeoCodesResponse.geocodeIDs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, message, geocodeIDs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetFoundGeoCodesResponse {\n");
    
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    geocodeIDs: ").append(toIndentedString(geocodeIDs)).append("\n");
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
