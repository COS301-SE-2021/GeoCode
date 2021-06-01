package tech.geocodeapp.geocode.GeoCode.Response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * UpdateAvailabilityResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-01T18:53:58.734Z[GMT]")


public class UpdateAvailabilityResponse   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("qrCode")
  private String qrCode = null;

  public UpdateAvailabilityResponse id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(description = "")
  
    @Valid
    public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UpdateAvailabilityResponse qrCode(String qrCode) {
    this.qrCode = qrCode;
    return this;
  }

  /**
   * Get qrCode
   * @return qrCode
   **/
  @Schema(description = "")
  
    public String getQrCode() {
    return qrCode;
  }

  public void setQrCode(String qrCode) {
    this.qrCode = qrCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateAvailabilityResponse updateAvailabilityResponse = (UpdateAvailabilityResponse) o;
    return Objects.equals(this.id, updateAvailabilityResponse.id) &&
        Objects.equals(this.qrCode, updateAvailabilityResponse.qrCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, qrCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateAvailabilityResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    qrCode: ").append(toIndentedString(qrCode)).append("\n");
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
