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
 * GetFoundCollectableTypesResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-24T10:49:36.244Z[GMT]")


public class GetFoundCollectableTypesResponse   {
  @JsonProperty("success")
  private Boolean success = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("collectableTypeIDs")
  @Valid
  private List<UUID> collectableTypeIDs = new ArrayList<UUID>();

  public GetFoundCollectableTypesResponse(boolean success, String message, List<UUID> collectableTypeIDs){
    this.success = success;
    this.message = message;
    this.collectableTypeIDs = collectableTypeIDs;
  }

  public GetFoundCollectableTypesResponse success(Boolean success) {
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

  public GetFoundCollectableTypesResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   **/
  @Schema(example = "The IDs of the User's found CollectableTypes was successfully returned", required = true, description = "")
      @NotNull

    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public GetFoundCollectableTypesResponse collectableTypeIDs(List<UUID> collectableTypeIDs) {
    this.collectableTypeIDs = collectableTypeIDs;
    return this;
  }

  public GetFoundCollectableTypesResponse addCollectableTypeIDsItem(UUID collectableTypeIDsItem) {
    this.collectableTypeIDs.add(collectableTypeIDsItem);
    return this;
  }

  /**
   * Get collectableTypeIDs
   * @return collectableTypeIDs
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<UUID> getCollectableTypeIDs() {
    return collectableTypeIDs;
  }

  public void setCollectableTypeIDs(List<UUID> collectableTypeIDs) {
    this.collectableTypeIDs = collectableTypeIDs;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetFoundCollectableTypesResponse getFoundCollectableTypesResponse = (GetFoundCollectableTypesResponse) o;
    return Objects.equals(this.success, getFoundCollectableTypesResponse.success) &&
        Objects.equals(this.message, getFoundCollectableTypesResponse.message) &&
        Objects.equals(this.collectableTypeIDs, getFoundCollectableTypesResponse.collectableTypeIDs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, message, collectableTypeIDs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetFoundCollectableTypesResponse {\n");
    
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    collectableTypeIDs: ").append(toIndentedString(collectableTypeIDs)).append("\n");
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
