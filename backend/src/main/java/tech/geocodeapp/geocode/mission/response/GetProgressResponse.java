package tech.geocodeapp.geocode.mission.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.general.response.Response;

import javax.validation.Valid;

/**
 * GetProgressResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-12T09:55:42.563Z[GMT]")


public class GetProgressResponse extends Response {
  @JsonProperty("progress")
  private Double progress = null;

  public GetProgressResponse(boolean success, String message,Double progress){
    super(success, message);
    this.progress = progress;
  }

  /**
   * Get progress
   * @return progress
   **/
  @Schema(example = "85", description = "")
  
    @Valid
    public Double getProgress() {
    return progress;
  }

  public void setProgress(double progress) {
    this.progress = progress;
  }
}
