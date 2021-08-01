package tech.geocodeapp.geocode.leaderboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Leaderboard
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-05T13:14:20.256Z[GMT]")

@Entity
@Table(name = "leaderboards")
public class Leaderboard   {
  @Id
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("name")
  private String name = null;

  public Leaderboard() {

  }

  public Leaderboard( String name ){
    this.id = UUID.randomUUID();
    this.name = name;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
  @NotNull
  @Valid
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(required = true, description = "")
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
