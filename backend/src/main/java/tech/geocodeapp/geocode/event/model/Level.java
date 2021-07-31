package tech.geocodeapp.geocode.event.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.geocode.model.GeoCode;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Level
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-29T21:10:38.710Z[GMT]")


public class Level {

  @JsonProperty( "target" )
  private GeoCode target = null;

  @JsonProperty( "onLevel" )
  @Valid
  private Map< String, UUID > onLevel = null;

  public Level target( GeoCode target ) {

    this.target = target;
    return this;
  }

  /**
   * Get target
   *
   * @return target
   **/
  @Schema( required = true, description = "" )
  @NotNull

  @Valid
  public GeoCode getTarget() {

    return target;
  }

  public void setTarget( GeoCode target ) {

    this.target = target;
  }

  public Level onLevel( Map< String, UUID > onLevel ) {

    this.onLevel = onLevel;
    return this;
  }

  public Level putOnLevelItem( String key, UUID onLevelItem ) {

    if ( this.onLevel == null ) {
      this.onLevel = new HashMap< String, UUID >();
    }
    this.onLevel.put( key, onLevelItem );
    return this;
  }

  /**
   * Get onLevel
   *
   * @return onLevel
   **/
  @Schema( description = "" )
  @Valid
  public Map< String, UUID > getOnLevel() {

    return onLevel;
  }

  public void setOnLevel( Map< String, UUID > onLevel ) {

    this.onLevel = onLevel;
  }


  @Override
  public boolean equals( java.lang.Object o ) {

    if ( this == o ) {
      return true;
    }
    if ( o == null || getClass() != o.getClass() ) {
      return false;
    }
    Level level = ( Level ) o;
    return Objects.equals( this.target, level.target ) &&
            Objects.equals( this.onLevel, level.onLevel );
  }

  @Override
  public int hashCode() {

    return Objects.hash( target, onLevel );
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append( "class Level {\n" );

    sb.append( "    target: " ).append( toIndentedString( target ) ).append( "\n" );
    sb.append( "    onLevel: " ).append( toIndentedString( onLevel ) ).append( "\n" );
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
