package tech.geocodeapp.geocode.Collectable.Decorator;

import io.swagger.model.Rarity;

import java.util.GregorianCalendar;
import java.util.UUID;

public interface CollectableTypeComponent {

    /**
     * Getters and setters for all variables that the concrete component includes
     */

    String getName();

    void setName(String name);

    Rarity getRarity();

    void setRarity(Rarity rarity);

    UUID getId();

    void setId(UUID id);

    /**
     * Getter for trackable used to get the trackable variable from {@link TrackableCollectableTypeDecorator}
     */
    boolean getTrackable();

    /**
     * Getter for expiryDate used to get the expiryDate variable from {@link ExpiringCollectableTypeDecorator}
     */
    GregorianCalendar getExpiryDate();

    /**
     * Setter for expiryDate used to set the expiryDate variable from {@link ExpiringCollectableTypeDecorator}
     */
    void setExpiryDate(GregorianCalendar date);

    /**
     * Getter for area used to get the area variable from {@link GeofencedCollectableTypeDecorator}
     */
    String getArea();

    /**
     * Setter for area used to set the area variable from {@link GeofencedCollectableTypeDecorator}
     */
    void setArea(String area);
}
