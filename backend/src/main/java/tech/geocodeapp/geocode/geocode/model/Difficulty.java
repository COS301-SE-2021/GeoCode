package tech.geocodeapp.geocode.geocode.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;

/**
 * The GeoCode's real world locating difficulty
 */
public enum Difficulty {

    EASY( "EASY" ),
    MEDIUM( "MEDIUM" ),
    HARD( "HARD" ),
    INSANE( "INSANE" );

    /**
     * The value of the difficulty
     */
    private final String value;

    /**
     * Overload Constructor
     *
     * @param value the enum to be created
     */
    Difficulty( String value ) {

        this.value = value;
    }

    /**
     * Get all the values in the enum
     *
     * @return the values the enum can take on
     */
    public static List< Difficulty > getDifficultyOrder() {

        return Arrays.asList( Difficulty.values() );
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
    public static Difficulty fromValue( String text ) {

        for ( Difficulty b : Difficulty.values() ) {

            if ( String.valueOf( b.value ).equals( text ) ) {

                return b;
            }
        }

        return null;
    }
}
