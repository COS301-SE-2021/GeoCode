package tech.geocodeapp.geocode.Collectable.Decorator;

import io.swagger.model.Rarity;

import java.util.GregorianCalendar;
import java.util.UUID;

public class TrackableCollectableTypeDecorator extends CollectableTypeDecorator{

    /**
     * @param decoratedType the CollectableTypeComponent to decorate
     */
    public TrackableCollectableTypeDecorator(CollectableTypeComponent decoratedType) {
        super(decoratedType);
    }

    /**
     * @return the value returned by decoratedType's getName() method
     */
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * Sets the decoratedType's name variable
     * @param name the name of the CollectableType
     */
    @Override
    public void setName(String name) {
        super.setName(name);
    }

    /**
     * @return the decoratedType's rarity
     */
    @Override
    public Rarity getRarity() {
        return super.getRarity();
    }

    /**
     * Sets the decoratedType's rarity
     * @param rarity a Enum for how common a CollectableType is
     */
    @Override
    public void setRarity(Rarity rarity) {
        super.setRarity(rarity);
    }

    /**
     * @return the id of the decoratedType
     */
    @Override
    public UUID getId() {
        return super.getId();
    }

    /**
     * Sets the decoratedType's id
     * @param id the unique id to identify the CollectableType
     */
    @Override
    public void setId(UUID id) {
        super.setId(id);
    }

    /**
     * @return true as this class adds trackable functionality
     */
    @Override
    public boolean getTrackable() {
        return true;
    }

    /**
     * @return the expiryDate of the decoratedType
     */
    @Override
    public GregorianCalendar getExpiryDate() {
        return decoratedType.getExpiryDate();
    }

    /**
     * Sets the decoratedType's expiryDate
     * @param date the calendar date on which the collectableType expires
     */
    @Override
    public void setExpiryDate(GregorianCalendar date) {
        decoratedType.setExpiryDate(date);
    }

    /**
     * @return the area a decoratedType may not leave if one is specified
     */
    @Override
    public String getArea() {
        return decoratedType.getArea();
    }

    /**
     * Sets the decoratedType's area
     * @param area the area a collectable type is geographically bound to
     */
    @Override
    public void setArea(String area) {
        decoratedType.setArea(area);
    }
}
