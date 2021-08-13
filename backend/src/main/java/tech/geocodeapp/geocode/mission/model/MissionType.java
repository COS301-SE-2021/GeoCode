package tech.geocodeapp.geocode.mission.model;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The types of Missions
 */
public enum MissionType {
  CIRCUMFERENCE("Circumference"),
    GEOCODE("GeoCode"),
    SWAP("Swap"),
    RANDOM("Random");

  private final String value;

  MissionType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static MissionType fromValue(String text) {
    for (MissionType b : MissionType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
