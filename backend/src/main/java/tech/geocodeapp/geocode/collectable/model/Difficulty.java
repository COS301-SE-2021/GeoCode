package tech.geocodeapp.geocode.collectable.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The GeoCode's real world locating difficulty
 */
public enum Difficulty {
  EASY("EASY"),
    MEDIUM("MEDIUM"),
    DIFFICULTY("DIFFICULTY"),
    INSANE("INSANE");

  private String value;

  Difficulty(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Difficulty fromValue(String text) {
    for (Difficulty b : Difficulty.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
