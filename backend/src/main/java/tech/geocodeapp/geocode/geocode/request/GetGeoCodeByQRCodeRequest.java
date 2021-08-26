package tech.geocodeapp.geocode.geocode.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GetGeoCodeByQRCodeRequest used to specify the attributes needed to locate a specific GeoCode
 * in the database
 */
@Validated
public class GetGeoCodeByQRCodeRequest {

    /**
     * The QRCode of a specific GeoCode used to locate it
     */
    @JsonProperty( "qrCode" )
    private String qrCode = null;

    /**
     * Default constructor
     */
    public GetGeoCodeByQRCodeRequest() {

    }

    /**
     * Overloaded Constructor
     *
     * @param qrCode The QRCode of a specific GeoCode used to locate it
     */
    public GetGeoCodeByQRCodeRequest( String qrCode ) {

        this.qrCode = qrCode;
    }

    /**
     * Sets the qrCode attribute to the specified value
     *
     * @param qrCode the value the attribute should be set to
     *
     * @return the request after the qrCode has been changed
     */
    public GetGeoCodeByQRCodeRequest qrCode( String qrCode ) {

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

        return Objects.equals( this.qrCode, ( ( GetGeoCodeByQRCodeRequest ) obj ).qrCode );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( qrCode );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetGeoCodeByQRCodeRequest {\n" +
                "    qrCode: " + toIndentedString( qrCode ) + "\n" +
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
