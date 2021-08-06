package tech.geocodeapp.geocode.geocode.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;
import java.util.UUID;

/**
 * GetCollectablesInGeoCodeByQRCodeRequest used to specify the attributes needed to locate a specific GeoCode
 * in the database
 */
@Validated
public class GetCollectablesInGeoCodeByQRCodeRequest {

    /**
     * The QRCode of a specific GeoCode used to locate it
     */
    @JsonProperty( "QRCode" )
    private String qrCode = null;

    /**
     * The unique identifier of a specific GeoCode
     */
    @JsonProperty( "geoCodeID" )
    private UUID geoCodeID = null;

    /**
     * Default constructor
     */
    public GetCollectablesInGeoCodeByQRCodeRequest() {

    }

    /**
     * Overloaded Constructor
     *
     * @param qrCode The QRCode of a specific GeoCode used to locate it
     * @param geoCodeID The unique identifier of a specific GeoCode
     */
    public GetCollectablesInGeoCodeByQRCodeRequest( String qrCode, UUID geoCodeID ) {

        this.qrCode = qrCode;
        this.geoCodeID = geoCodeID;
    }

    /**
     * Sets the qrCode attribute to the specified value
     *
     * @param qrCode the value the attribute should be set to
     *
     * @return the request after the qrCode has been changed
     */
    public GetCollectablesInGeoCodeByQRCodeRequest qrCode( String qrCode ) {

        this.qrCode = qrCode;
        return this;
    }

    /**
     * Gets the saved qrCode attribute
     *
     * @return the stored qrCode attribute
     */
    public String getQrCode() {

        return qrCode;
    }

    /**
     * Sets the qrCode attribute to the specified value
     *
     * @param qrCode the value the attribute should be set to
     */
    public void setQrCode( String qrCode ) {

        this.qrCode = qrCode;
    }

    /**
     * Sets the geoCodeID attribute to the specified value
     *
     * @param geoCodeID the value the attribute should be set to
     *
     * @return the request after the geoCodeID has been changed
     */
    public GetCollectablesInGeoCodeByQRCodeRequest geoCodeID( UUID geoCodeID ) {

        this.geoCodeID = geoCodeID;
        return this;
    }

    /**
     * Gets the saved geoCodeID attribute
     *
     * @return the stored geoCodeID attribute
     */
    @Valid
    public UUID getGeoCodeID() {

        return geoCodeID;
    }

    /**
     * Sets the geoCodeID attribute to the specified value
     *
     * @param geoCodeID the value the attribute should be set to
     */
    public void setGeoCodeID( UUID geoCodeID ) {

        this.geoCodeID = geoCodeID;
    }

    /**
     * Determines if the specified object is the same as the current object
     *
     * @param obj the object we want to compare with the specific attributes of this class
     *
     * @return if the object is the same or not
     */
    @Override
    public boolean equals( java.lang.Object obj ) {

        if ( this == obj ) {

            return true;
        }
        if ( obj == null || getClass() != obj.getClass() ) {

            return false;
        }

        var getCollectablesInGeoCodeByQRCodeRequest = ( GetCollectablesInGeoCodeByQRCodeRequest ) obj;
        return Objects.equals( this.qrCode, getCollectablesInGeoCodeByQRCodeRequest.qrCode ) &&
               Objects.equals( this.geoCodeID, getCollectablesInGeoCodeByQRCodeRequest.geoCodeID );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( qrCode, geoCodeID );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetCollectablesInGeoCodeByQRCodeRequest {\n" +
                "    qrCode: " + toIndentedString( qrCode ) + "\n" +
                "    geoCodeID: " + toIndentedString( geoCodeID ) + "\n" +
                "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString( java.lang.Object o ) {

        if ( o == null ) {

            return "null";
        }

        return o.toString().replace( "\n", "\n    " );
    }

}
