package tech.geocodeapp.geocode.mission.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;

/**
 * CreateMissionRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-13T15:58:34.861Z[GMT]")


public class CreateMissionRequest   {
  @JsonProperty("collectableID")
  private UUID collectableID = null;

  public CreateMissionRequest(UUID collectableID){
    this.collectableID = collectableID;
  }

  public UUID getCollectableID() {
    return collectableID;
  }

  public void setCollectableID(UUID collectableID) {
    this.collectableID = collectableID;
  }
}
