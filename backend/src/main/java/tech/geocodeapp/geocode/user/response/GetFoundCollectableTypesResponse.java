package tech.geocodeapp.geocode.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.general.Response;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetFoundCollectableTypesResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-24T10:49:36.244Z[GMT]")


public class GetFoundCollectableTypesResponse extends Response {
  @JsonProperty("collectableTypeIDs")
  @Valid
  private List<UUID> collectableTypeIDs = new ArrayList<UUID>();

  public GetFoundCollectableTypesResponse(boolean success, String message, List<UUID> collectableTypeIDs){
    super(success, message);
    this.collectableTypeIDs = collectableTypeIDs;
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
