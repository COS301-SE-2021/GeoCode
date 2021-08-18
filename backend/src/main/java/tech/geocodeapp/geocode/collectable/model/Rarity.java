package tech.geocodeapp.geocode.collectable.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets Rarity
 */
public enum Rarity {
  COMMON("COMMON"),
  UNCOMMON("UNCOMMON"),
  RARE("RARE"),
  EPIC("EPIC"),
  LEGENDARY("LEGENDARY"),
  UNIQUE("UNIQUE");

  private String value;

  Rarity(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Rarity fromValue(String text) {
    for (Rarity b : Rarity.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

  public double getProbability() {
    switch(this) {
      case COMMON: return 0.5;
      case UNCOMMON: return 0.3;
      case RARE: return 0.15;
      case EPIC: return 0.04;
      case LEGENDARY: return 0.01;
      default: return 0;
    }
  }
}
