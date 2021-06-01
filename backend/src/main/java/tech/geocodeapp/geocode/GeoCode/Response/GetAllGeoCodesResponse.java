package tech.geocodeapp.geocode.GeoCode.Response;

import org.springframework.stereotype.Component;
import tech.geocodeapp.geocode.GeoCode.Model.GeoCode;

import java.util.ArrayList;

@Component
public class GetAllGeoCodesResponse {

    /**A list of all the created GeoCodes in the repo*/
    ArrayList< GeoCode > allGeoCodes;

    /**
     * Default Constructor
     */
    public GetAllGeoCodesResponse() {

    }

    /**
     * Overloaded Constructor that initializes all attributes
     *
     * @param allGeoCodes the list to set the allGeoCodes list attribute to
     */
    public GetAllGeoCodesResponse( ArrayList< GeoCode > allGeoCodes ) {

        this.allGeoCodes = allGeoCodes;
    }

    /**
     * Get the stored allGeoCodes attribute
     *
     * @return the specified allGeoCodes attribute
     */
    public ArrayList< GeoCode > getAllGeoCodes() {

        return allGeoCodes;
    }

    /**
     * Set the allGeoCodes attribute to the given
     *
     * @param allGeoCodes the list to set the allGeoCodes list attribute to
     */
    public void setAllGeoCodes( ArrayList< GeoCode > allGeoCodes ) {

        this.allGeoCodes = allGeoCodes;
    }

}