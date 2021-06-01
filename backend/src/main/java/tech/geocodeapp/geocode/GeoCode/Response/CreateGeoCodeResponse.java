package tech.geocodeapp.geocode.GeoCode.Response;

import org.springframework.stereotype.Component;

@Component
public class CreateGeoCodeResponse {

    private long id;
    private String qrCode;

    /**
     * Default constructor
     */
    public CreateGeoCodeResponse() {

    }

    public CreateGeoCodeResponse( long id, String qrCode ) {

        this.id = id;
        this.qrCode = qrCode;
    }

    /**
     * Get the stored id attribute
     *
     * @return the specified id attribute
     */
    public Long getId() {

        return id;
    }

    /**
     * Set the id attribute to the given
     *
     * @param id the long value to set the id attribute to
     */
    public void setId( Long id ) {

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