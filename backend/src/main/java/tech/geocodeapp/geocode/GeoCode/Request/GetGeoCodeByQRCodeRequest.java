package tech.geocodeapp.geocode.GeoCode.Request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * GetGeoCodeByQRCodeRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-19T20:06:36.916Z[GMT]")


public class GetGeoCodeByQRCodeRequest   {
  @JsonProperty("QRCode")
  private String qrCode = null;

  public GetGeoCodeByQRCodeRequest qrCode(String qrCode) {
    this.qrCode = qrCode;
    return this;
  }

  /**
   * Get qrCode
   * @return qrCode
   **/
  @Schema(required = true, description = "")
      @NotNull

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
    GetGeoCodeByQRCodeRequest getGeoCodeByQRCodeRequest = (GetGeoCodeByQRCodeRequest) o;
    return Objects.equals(this.qrCode, getGeoCodeByQRCodeRequest.qrCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(qrCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetGeoCodeByQRCodeRequest {\n");
    
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
