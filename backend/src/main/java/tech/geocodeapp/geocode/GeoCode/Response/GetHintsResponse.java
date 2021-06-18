package tech.geocodeapp.geocode.GeoCode.Response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetHintsResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-18T04:07:38.973Z[GMT]")


public class GetHintsResponse   {
  @JsonProperty("hints")
  @Valid
  private List<String> hints = null;

  public GetHintsResponse hints(List<String> hints) {
    this.hints = hints;
    return this;
  }

  public GetHintsResponse addHintsItem(String hintsItem) {
    if (this.hints == null) {
      this.hints = new ArrayList<String>();
    }
    this.hints.add(hintsItem);
    return this;
  }

  /**
   * Get hints
   * @return hints
   **/
  @Schema(description = "")
  
    public List<String> getHints() {
    return hints;
  }

  public void setHints(List<String> hints) {
    this.hints = hints;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetHintsResponse getHintsResponse = (GetHintsResponse) o;
    return Objects.equals(this.hints, getHintsResponse.hints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hints);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetHintsResponse {\n");
    
    sb.append("    hints: ").append(toIndentedString(hints)).append("\n");
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
