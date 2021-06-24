package tech.geocodeapp.geocode.User.Response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.Collectable.Model.Collectable;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetOwnedGeoCodesResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-24T10:49:36.244Z[GMT]")


public class GetOwnedGeoCodesResponse   {
  @JsonProperty("success")
  private Boolean success = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("geocodeIDs")
  @Valid
  private List<UUID> geocodeIDs = new ArrayList<UUID>();

  public GetOwnedGeoCodesResponse(boolean success, String message, List<UUID> geocodeIDs) {
    this.success = success;
    this.message = message;
    this.geocodeIDs = geocodeIDs;
  }

  public GetOwnedGeoCodesResponse success(Boolean success) {
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

  public GetOwnedGeoCodesResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   **/
  @Schema(example = "The IDs of the User's owned GeoCodes was successfully returned", required = true, description = "")
      @NotNull

    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public GetOwnedGeoCodesResponse geocodeIDs(List<UUID> geocodeIDs) {
    this.geocodeIDs = geocodeIDs;
    return this;
  }

  public GetOwnedGeoCodesResponse addGeocodeIDsItem(UUID geocodeIDsItem) {
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
    GetOwnedGeoCodesResponse getOwnedGeoCodesResponse = (GetOwnedGeoCodesResponse) o;
    return Objects.equals(this.success, getOwnedGeoCodesResponse.success) &&
        Objects.equals(this.message, getOwnedGeoCodesResponse.message) &&
        Objects.equals(this.geocodeIDs, getOwnedGeoCodesResponse.geocodeIDs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, message, geocodeIDs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetOwnedGeoCodesResponse {\n");
    
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
