package tech.geocodeapp.geocode.GeoCode.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;


import tech.geocodeapp.geocode.GeoCode.Model.GeoCode.DifficultyLevel;

@Component
public class CreateGeoCodeRequest {

    private String description, collectables, trackables, location;
    private DifficultyLevel difficulty;
    private String[] hints;

    /**
     * Default constructor
     */
    public CreateGeoCodeRequest() {

    }

    /**
     * Overloaded Constructor that sets the attributes to the given values
     *        - This class does not set the Trackable attribute
     *
     * @param description the given description of the GeoCode's surrounding and details
     * @param hints the given list of hints that will assist the user in locating the physical GeoCode/QR Code
     * @param difficulty the given level of difficulty there is in finding the physical GeoCode/QR Code
     * @param collectables the given collectable that is associated at this GeoCode
     * @param location the given physical location, of where the QR Code is stored, in the real world
     */
    public CreateGeoCodeRequest( @JsonProperty("description") String description, @JsonProperty("collectables") String collectables,
                                 @JsonProperty("location") String location, @JsonProperty("hints") String[] hints,
                                 @JsonProperty("difficulty") DifficultyLevel difficulty ) {

        this.description = description;
        this.collectables = collectables;
        this.location = location;
        this.hints = hints;
        this.difficulty = difficulty;
    }

    /**
     * Overloaded Constructor that sets the attributes to the given values
     *        - This class does not set the Trackable attribute
     *        - This class does not set the Collectable attribute
     *
     * @param description the given description of the GeoCode's surrounding and details
     * @param hints the given list of hints that will assist the user in locating the physical GeoCode/QR Code
     * @param difficulty the given level of difficulty there is in finding the physical GeoCode/QR Code
     * @param location the given physical location, of where the QR Code is stored, in the real world
     */
    public CreateGeoCodeRequest( @JsonProperty("description") String description, @JsonProperty("location") String location,
                                 @JsonProperty("hints") String[] hints, @JsonProperty("difficulty") DifficultyLevel difficulty ) {

        this.description = description;
        this.location = location;
        this.hints = hints;
        this.difficulty = difficulty;
    }

    /**
     * Overloaded Constructor that sets the attributes to the given values
     *
     * @param description the given description of the GeoCode's surrounding and details
     * @param hints the given list of hints that will assist the user in locating the physical GeoCode/QR Code
     * @param difficulty the given level of difficulty there is in finding the physical GeoCode/QR Code
     * @param collectables the given collectable that is associated at this GeoCode
     * @param trackables the given trackable that is associated at this GeoCode
     * @param location the given physical location, of where the QR Code is stored, in the real world
     */
    public CreateGeoCodeRequest( @JsonProperty("description") String description, @JsonProperty("collectables") String collectables,
                                 @JsonProperty("trackables") String trackables, @JsonProperty("location") String location,
                                 @JsonProperty("hints") String[] hints, @JsonProperty("difficulty") DifficultyLevel difficulty ) {

        this.description = description;
        this.collectables = collectables;
        this.trackables = trackables;
        this.location = location;
        this.hints = hints;
        this.difficulty = difficulty;
    }

    /**
     * Get the stored description in the table
     *
     * @return the specified description attribute
     */
    public String getDescription() {

        return description;
    }

    /**
     * Set the description in the table to the given
     *
     * @param description the description to set the description attribute to
     */
    public void setDescription( String description ) {

        this.description = description;
    }

    /**
     * Get the stored hints in the table
     *
     * @return the specified hints attribute
     */
    public String[] getHints() {

        return hints;
    }

    /**
     * Set the hints in the table to the given
     *
     * @param hints the hints to set the hints attribute to
     */
    public void setHints( String[] hints ) {

        this.hints = hints;
    }

    /**
     * Get the stored difficulty in the table
     *
     * @return the specified difficulty attribute
     */
    public DifficultyLevel getDifficulty() {

        return difficulty;
    }

    /**
     * Set the difficulty in the table to the given
     *
     * @param difficulty the difficulty to set the difficulty attribute to
     */
    public void setDifficulty( DifficultyLevel difficulty ) {

        this.difficulty = difficulty;
    }

    /**
     * Get the stored collectables in the table
     *
     * @return the specified collectables attribute
     */
    public String getCollectables() {

        return collectables;
    }

    /**
     * Set the collectables in the table to the given
     *
     * @param collectables the collectables to set the collectables attribute to
     */
    public void setCollectables( String collectables ) {

        this.collectables = collectables;
    }

    /**
     * Get the stored trackables in the table
     *
     * @return the specified trackables attribute
     */
    public String getTrackables() {

        return trackables;
    }

    /**
     * Set the trackables in the table to the given
     *
     * @param trackables the trackables to set the trackables attribute to
     */
    public void setTrackables( String trackables ) {

        this.trackables = trackables;
    }

    /**
     * Get the stored location in the table
     *
     * @return the specified location attribute
     */
    public String getLocation() {

        return location;
    }

    /**
     * Set the location in the table to the given
     *
     * @param location the location to set the location attribute to
     */
    public void setLocation( String location ) {

        this.location = location;
    }

}
