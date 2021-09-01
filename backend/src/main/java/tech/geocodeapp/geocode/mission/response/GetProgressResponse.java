package tech.geocodeapp.geocode.mission.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.general.response.Response;

/**
 * GetProgressResponse
 */
public class GetProgressResponse extends Response {
  @JsonProperty("progress")
  private Double progress;

  public GetProgressResponse(boolean success, String message,Double progress){
    super(success, message);
    this.progress = progress;
  }

  public Double getProgress() {
    return progress;
  }

  public void setProgress(double progress) {
    this.progress = progress;
  }
}
