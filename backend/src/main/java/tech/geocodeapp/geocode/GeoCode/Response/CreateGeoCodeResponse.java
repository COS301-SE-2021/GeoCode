package tech.geocodeapp.geocode.GeoCode.Response;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateGeoCodeResponse {

    private UUID id;
    private String qrCode;

    /**
     * Default constructor
     */
    public CreateGeoCodeResponse() {

    }

    public CreateGeoCodeResponse( UUID id, String qrCode ) {

        this.id = id;
        this.qrCode = qrCode;
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
     * @param id the long value to set the id attribute to
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
     * @param qrCode the hints to set the location attribute to
     */
    public void setQrCode( String qrCode ) {

        this.qrCode = qrCode;
    }

}