package tech.geocodeapp.geocode.geocode.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.validation.constraints.*;

/**
 * GeoPoint
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-07T10:35:03.795Z[GMT]")

@Embeddable
@Table( name = "geopoint" )
public class GeoPoint   {
    @JsonProperty("latitude")
    private Float latitude = null;

    @JsonProperty("longitude")
    private Float longitude = null;

    public GeoPoint latitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * Get latitude
     * @return latitude
     **/
    @Schema(example = "25.7545", required = true, description = "")
    @NotNull

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public GeoPoint longitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * Get longitude
     * @return longitude
     **/
    @Schema(example = "28.2314", required = true, description = "")
    @NotNull

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GeoPoint geoPoint = (GeoPoint) o;
        return Objects.equals(this.latitude, geoPoint.latitude) &&
                Objects.equals(this.longitude, geoPoint.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GeoPoint {\n");

        sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
        sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
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
