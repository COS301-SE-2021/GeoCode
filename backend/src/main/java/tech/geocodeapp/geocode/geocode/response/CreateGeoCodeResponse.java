package tech.geocodeapp.geocode.geocode.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.general.response.Response;

import javax.validation.Valid;
import java.util.UUID;

/**
 * CreateGeoCodeResponse used to access the attributes received to create the response
 * of if a GeoCode was created
 */
@Validated
public class CreateGeoCodeResponse extends Response {
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
    public CreateGeoCodeResponse( Boolean success, String message ) {
        super(success, message);
    }

    /**
     * Overloaded Constructor
     *
     * @param success Determines if the creation of a GeoCode with the specified attributes in the request was a success or not
     * @param geoCodeID The unique identifier of the created GeoCode
     * @param qrCode The QRCode of the created GeoCode
     */
    public CreateGeoCodeResponse( Boolean success, String message, UUID geoCodeID, String qrCode ) {

        super(success, message);

        this.geoCodeID = geoCodeID;
        this.qrCode = qrCode;
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

}
