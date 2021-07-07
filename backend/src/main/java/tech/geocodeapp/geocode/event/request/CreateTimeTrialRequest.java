package tech.geocodeapp.geocode.event.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateTimeTrialRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-07T10:35:03.795Z[GMT]")


public class CreateTimeTrialRequest {

    @JsonProperty( "timeLimit" )
    private BigDecimal timeLimit = null;

    public CreateTimeTrialRequest timeLimit( BigDecimal timeLimit ) {

        this.timeLimit = timeLimit;
        return this;
    }

    /**
     * Get timeLimit
     *
     * @return timeLimit
     **/
    @Schema( required = true, description = "" )
    @NotNull

    @Valid
    public BigDecimal getTimeLimit() {

        return timeLimit;
    }

    public void setTimeLimit( BigDecimal timeLimit ) {

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
        CreateTimeTrialRequest createTimeTrialRequest = ( CreateTimeTrialRequest ) o;
        return Objects.equals( this.timeLimit, createTimeTrialRequest.timeLimit );
    }

    @Override
    public int hashCode() {

        return Objects.hash( timeLimit );
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append( "class CreateTimeTrialRequest {\n" );

        sb.append( "    timeLimit: " ).append( toIndentedString( timeLimit ) ).append( "\n" );
        sb.append( "}" );
        return sb.toString();
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
