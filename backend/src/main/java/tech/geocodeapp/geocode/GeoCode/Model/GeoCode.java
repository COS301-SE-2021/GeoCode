package tech.geocodeapp.geocode.GeoCode.Model;

import javax.persistence.*;

/**
 * This model represents the GeoCode table inside of the database,
 * it stores the relevant attributes and primary key to locate entries.
 */
@Entity
@Table( name="GeoCode" )
public class GeoCode {

    /**
     *
     *  DIFFICULTYLEVEL enumeration
     *                 - this indicates the level of difficulty there is in finding the
     *                   physical GeoCode/QR Code
     */
    public enum DIFFICULTYLEVEL {

        Easy,
        Medium,
        Difficult,
        Insane
    }


    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Column( name = "description" )
    private String description;

    @Column( name = "hints" )
    private String hints;

    @Column( name = "difficulty" )
    private DIFFICULTYLEVEL difficulty;

    @Column( name = "collectables" )
    private String collectables;

    @Column( name = "trackables" )
    private String trackables;

    @Column( name = "qrCode" )
    private String qrCode;

    @Column( name = "location" )
    private String location;

    /**
     * Default Constructor.
     */
    public GeoCode() {

    }

    /**
     * Overloaded Constructor that will set all the attributes to the given attributes.
     *
     * @param id the unique identifier in the table, so that it is easier to locate entries.
     * @param description the given description of the GeoCode's surrounding and details
     * @param hints the given list of hints that will assist the user in locating the physical GeoCode/QR Code
     * @param difficulty the given level of difficulty there is in finding the physical GeoCode/QR Code
     * @param collectables the given collectable that is associated at this GeoCode
     * @param trackables the given tracbable that is associated at this GeoCode
     * @param qrCode the given QR Code that connects this class to the real world
     * @param location the given physical location, of where the QR Code is stored, in the real world
     */
    public GeoCode( Long id, String description, String hints, DIFFICULTYLEVEL difficulty, String collectables, String trackables, String qrCode, String location ) {

        this.id = id;
        this.description = description;
        this.hints = hints;
        this.difficulty = difficulty;
        this.collectables = collectables;
        this.trackables = trackables;
        this.qrCode = qrCode;
        this.location = location;
    }

    /**
     * Get the stored id in the table
     *
     * @return the specified id attribute
     */
    @Id
    public Long getId() {

        return id;
    }

    /**
     * Set the id in the table to the given
     *
     * @param id the long value to set the id attribute to
     */
    public void setId( Long id ) {

        this.id = id;
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
    public String getHints() {

        return hints;
    }

    /**
     * Set the hints in the table to the given
     *
     * @param hints the hints to set the hints attribute to
     */
    public void setHints( String hints ) {

        this.hints = hints;
    }

    /**
     * Get the stored difficulty in the table
     *
     * @return the specified difficulty attribute
     */
    public DIFFICULTYLEVEL getDifficulty() {

        return difficulty;
    }

    /**
     * Set the difficulty in the table to the given
     *
     * @param difficulty the difficulty to set the difficulty attribute to
     */
    public void setDifficulty( DIFFICULTYLEVEL difficulty ) {

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
     * Get the stored qrCode in the table
     *
     * @return the specified qrCode attribute
     */
    public String getQrCode() {

        return qrCode;
    }

    /**
     * Set the location in the table to the given
     *
     * @param qrCode the hints to set the location attribute to
     */
    public void setQrCode( String qrCode ) {

        this.qrCode = qrCode;
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
