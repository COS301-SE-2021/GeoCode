package tech.geocodeapp.geocode.event.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CreateTimeTrialRequest
 */
@Validated
public class CreateTimeTrialRequest {

    @JsonProperty( "timeLimit" )
    private double timeLimit;

    public CreateTimeTrialRequest timeLimit( double timeLimit ) {

        this.timeLimit = timeLimit;
        return this;
    }

    public double getTimeLimit() {

        return timeLimit;
    }

    public void setTimeLimit( double timeLimit ) {

        this.timeLimit = timeLimit;
    }

    @Override
    public boolean equals( java.lang.Object o ) {

        if ( this == o ) {

            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {

            return false;
        }

        return Objects.equals( this.timeLimit, ( ( CreateTimeTrialRequest ) o ).timeLimit );
    }

    @Override
    public int hashCode() {

        return Objects.hash( timeLimit );
    }

    @Override
    public String toString() {

        return "class CreateTimeTrialRequest {\n" +
                "    timeLimit: " + toIndentedString( timeLimit ) + "\n" +
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
