package tech.geocodeapp.geocode.Collectable.Decorator;

import io.swagger.model.Rarity;

import java.util.GregorianCalendar;
import java.util.UUID;

public abstract class CollectableTypeDecorator implements CollectableTypeComponent {
    //the decorated CollectableTypeComponent
    protected CollectableTypeComponent decoratedType;

    /**
     * @param decoratedType the CollectableTypeComponent to decorate
     */
    public CollectableTypeDecorator(CollectableTypeComponent decoratedType) {
        this.decoratedType = decoratedType;
    }

    /**
     * @return the value returned by decoratedType's getName() method
     */
    public String getName() {
       return decoratedType.getName();
    }

    /**
     * Sets the decoratedType's name variable
     * @param name the name of the CollectableType
     */
    public void setName(String name) {
        decoratedType.setName(name);
    }

    /**
     * @return the decoratedType's rarity
     */
    public Rarity getRarity() {
        return decoratedType.getRarity();
    }

    /**
     * Sets the decoratedType's rarity
     * @param rarity a Enum for how common a CollectableType is
     */
    public void setRarity(Rarity rarity) {
        decoratedType.setRarity(rarity);
    }

    /**
     * @return the id of the decoratedType
     */
    public UUID getId() {
        return decoratedType.getId();
    }

    /**
     * Sets the decoratedType's id
     * @param id the unique id to identify the CollectableType
     */
    public void setId(UUID id) {
        decoratedType.setId(id);
    }

    /**
     * @return true or false based on if the decoratedType is trackable or not
     */
    public boolean getTrackable() {
        return decoratedType.getTrackable();
    }

    /**
     * @return the expiryDate of the decoratedType
     */
    public GregorianCalendar getExpiryDate() {
        return decoratedType.getExpiryDate();
    }

    /**
     * Sets the decoratedType's expiryDate
     * @param date the calendar date on which the collectableType expires
     */
    public void setExpiryDate(GregorianCalendar date) {
        decoratedType.setExpiryDate(date);
    }

    /**
     * @return the area a decoratedType may not leave if one is specified
     */
    public String getArea() {
        return decoratedType.getArea();
    }

    /**
     * Sets the decoratedType's area
     * @param area the area a collectable type is geographically bound to
     */
    public void setArea(String area) {
        decoratedType.setArea(area);
    }
}
