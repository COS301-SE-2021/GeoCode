package tech.geocodeapp.geocode.event.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.event.model.TimeTrial;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateTimeTrialResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-07T10:35:03.795Z[GMT]")


public class CreateTimeTrialResponse {

    @JsonProperty( "timeTrial" )
    @Valid
    private List< TimeTrial > timeTrial = new ArrayList< TimeTrial >();

    public CreateTimeTrialResponse timeTrial( List< TimeTrial > timeTrial ) {

        this.timeTrial = timeTrial;
        return this;
    }

    public CreateTimeTrialResponse addTimeTrialItem( TimeTrial timeTrialItem ) {

        this.timeTrial.add( timeTrialItem );
        return this;
    }

    /**
     * Get timeTrial
     *
     * @return timeTrial
     **/
    @Schema( required = true, description = "" )
    @NotNull
    @Valid
    public List< TimeTrial > getTimeTrial() {

        return timeTrial;
    }

    public void setTimeTrial( List< TimeTrial > timeTrial ) {

        this.timeTrial = timeTrial;
    }


    @Override
    public boolean equals( java.lang.Object o ) {

        if ( this == o ) {

            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {

            return false;
        }
        CreateTimeTrialResponse createTimeTrialResponse = ( CreateTimeTrialResponse ) o;
        return Objects.equals( this.timeTrial, createTimeTrialResponse.timeTrial );
    }

    @Override
    public int hashCode() {

        return Objects.hash( timeTrial );
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append( "class CreateTimeTrialResponse {\n" );

        sb.append( "    timeTrial: " ).append( toIndentedString( timeTrial ) ).append( "\n" );
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
