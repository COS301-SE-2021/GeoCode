package tech.geocodeapp.geocode.event.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreatePointRequest
 */
@Validated
public class CreatePointRequest {

    @JsonProperty( "amount" )
    private BigDecimal amount = null;

    public CreatePointRequest amount( BigDecimal amount ) {

        this.amount = amount;
        return this;
    }

    /**
     * Get amount
     *
     * @return amount
     **/
    @Schema( required = true, description = "" )
    @NotNull

    @Valid
    public BigDecimal getAmount() {

        return amount;
    }

    public void setAmount( BigDecimal amount ) {

        this.amount = amount;
    }


    @Override
    public boolean equals( java.lang.Object o ) {

        if ( this == o ) {

            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {

            return false;
        }
        CreatePointRequest createPointRequest = ( CreatePointRequest ) o;
        return Objects.equals( this.amount, createPointRequest.amount );
    }

    @Override
    public int hashCode() {

        return Objects.hash( amount );
    }

    @Override
    public String toString() {

        return "class CreatePointRequest {\n" +
                "    amount: " + toIndentedString( amount ) + "\n" +
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
