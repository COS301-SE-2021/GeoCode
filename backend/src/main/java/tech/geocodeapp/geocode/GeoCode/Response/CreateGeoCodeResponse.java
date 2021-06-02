package tech.geocodeapp.geocode.GeoCode.Response;

import org.springframework.stereotype.Component;
import tech.geocodeapp.geocode.GeoCode.Model.GeoCode;

import java.util.UUID;

@Component
public class CreateGeoCodeResponse {

    private UUID id;
    private String qrCode;
    private GeoCode geoCode;

    /**
     * Default constructor
     */
    public CreateGeoCodeResponse() {

    }

    /**
     * Overloaded Constructor with no geoCode
     *
     * @param id the UUID value to set the id attribute to
     * @param qrCode the qrCode to set the qrCode attribute to
     */
    public CreateGeoCodeResponse( UUID id, String qrCode ) {

        this.id = id;
        this.qrCode = qrCode;
        this.geoCode = null;
    }

    /**
     * Overloaded Constructor with all attributes initialised
     *
     * @param id the UUID value to set the id attribute to
     * @param qrCode the qrCode to set the qrCode attribute to
     * @param geoCode the geoCode to set the geoCode attribute to
     */
    public CreateGeoCodeResponse( UUID id, String qrCode, GeoCode geoCode ) {

        this.id = id;
        this.qrCode = qrCode;
        this.geoCode = geoCode;
    }

    /**
     * Get the stored id attribute
     *
     * @return the specified id attribute
     */
    public UUID getId() {

        return id;
    }

    /**
     * Set the id attribute to the given
     *
     * @param id the UUID value to set the id attribute to
     */
    public void setId( UUID id ) {

        this.id = id;
    }

    /**
     * Get the stored qrCode attribute
     *
     * @return the specified qrCode attribute
     */
    public String getQrCode() {

        return qrCode;
    }

    /**
     * Set the qrCode attribute
     *
     * @param qrCode the qrCode to set the qrCode attribute to
     */
    public void setQrCode( String qrCode ) {

        this.qrCode = qrCode;
    }

    /**
     * Get the stored geoCode attribute
     *
     * @return the specified geoCode attribute
     */
    public GeoCode getGeoCode() {

        return geoCode;
    }

    /**
     * Set the geoCode attribute
     *
     * @param geoCode the geoCode to set the geoCode attribute to
     */
    public void setGeoCode( GeoCode geoCode ) {

        this.geoCode = geoCode;
    }

}