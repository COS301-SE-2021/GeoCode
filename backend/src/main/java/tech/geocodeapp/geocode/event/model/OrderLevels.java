package tech.geocodeapp.geocode.event.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The order an Event should follow with completing Levels
 */
public enum OrderLevels {

    GIVEN( "GIVEN" ),
    DIFFICULTY( "DIFFICULTY" ),
    DISTANCE( "DISTANCE" );

    /**
     * The value of the order
     */
    private final String value;

    /**
     * Overload Constructor
     *
     * @param value the enum to be created
     */
    OrderLevels( String value ) {

        this.value = value;
    }

    /**
     * Get the enum stored as a string
     *
     * @return the string version of the enum
     */
    @Override
    @JsonValue
    public String toString() {

        return String.valueOf( value );
    }

    /**
     * Get the current value of the difficulty
     *
     * @param text the value we want to get as an enum
     *
     * @return the enum that corresponds to the value
     */
    @JsonCreator
    public static OrderLevels fromValue( String text ) {

        for ( OrderLevels b : OrderLevels.values() ) {

            if ( String.valueOf( b.value ).equals( text ) ) {

                return b;
            }
        }

        return null;
    }

}
