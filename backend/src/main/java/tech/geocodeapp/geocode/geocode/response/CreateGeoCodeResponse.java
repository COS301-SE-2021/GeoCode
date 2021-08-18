package tech.geocodeapp.geocode.geocode.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;
import java.util.UUID;

/**
 * CreateGeoCodeResponse used to access the attributes received to create the response
 * of if a GeoCode was created
 */
@Validated
public class CreateGeoCodeResponse {

    /**
     * Determines if the creation of a GeoCode with the specified attributes
     * in the request was a success or not
     */
    @JsonProperty( "success" )
    private Boolean success;

    /**
     * The unique identifier of the created GeoCode
     */
    @JsonProperty( "geoCodeID" )
    private UUID geoCodeID;

    /**
     * The QRCode of the created GeoCode
     */
    @JsonProperty( "qrCode" )
    private String qrCode;

    /**
     * Default constructor
     */
    public CreateGeoCodeResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param success Determines if the creation of a GeoCode with the specified attributes in the request was a success or not
     */
    public CreateGeoCodeResponse( Boolean success ) {

        this.success = success;
    }

    /**
     * Overloaded Constructor
     *
     * @param success Determines if the creation of a GeoCode with the specified attributes in the request was a success or not
     * @param geoCodeID The unique identifier of the created GeoCode
     * @param qrCode The QRCode of the created GeoCode
     */
    public CreateGeoCodeResponse( Boolean success, UUID geoCodeID, String qrCode ) {

        this.success = success;
        this.geoCodeID = geoCodeID;
        this.qrCode = qrCode;
    }

    /**
     * Sets the success attribute to the specified value
     *
     * @param success the value the attribute should be set to
     *
     * @return the response after the success has been changed
     */
    public CreateGeoCodeResponse success( Boolean success ) {

        this.success = success;
        return this;
    }

    /**
     * Gets the saved success attribute
     *
     * @return the stored success attribute
     */
    public Boolean isSuccess() {

        return success;
    }

    /**
     * Sets the success attribute to the specified value
     *
     * @param success the value the attribute should be set to
     */
    public void setSuccess( Boolean success ) {

        this.success = success;
    }

    /**
     * Sets the geoCodeID attribute to the specified value
     *
     * @param geoCodeID the value the attribute should be set to
     *
     * @return the request after the geoCodeID has been changed
     */
    public CreateGeoCodeResponse geoCodeID( UUID geoCodeID ) {

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
     * Sets the qrCode attribute to the specified value
     *
     * @param qrCode the value the attribute should be set to
     *
     * @return the request after the qrCode has been changed
     */
    public CreateGeoCodeResponse qrCode( String qrCode ) {

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

        var createGeoCodeResponse = ( CreateGeoCodeResponse ) obj;
        return Objects.equals( this.success, createGeoCodeResponse.success ) &&
               Objects.equals( this.geoCodeID, createGeoCodeResponse.geoCodeID ) &&
               Objects.equals( this.qrCode, createGeoCodeResponse.qrCode );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( success, geoCodeID, qrCode );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class CreateGeoCodeResponse {\n" +
                "    success: " + toIndentedString( success ) + "\n" +
                "    geoCodeID: " + toIndentedString( geoCodeID ) + "\n" +
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
