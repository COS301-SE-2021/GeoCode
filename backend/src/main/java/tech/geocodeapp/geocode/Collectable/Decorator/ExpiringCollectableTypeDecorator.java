package tech.geocodeapp.geocode.Collectable.Decorator;

import tech.geocodeapp.geocode.Collectable.Model.CollectableSet;
import tech.geocodeapp.geocode.Collectable.Model.Rarity;

import java.util.GregorianCalendar;
import java.util.UUID;

public class ExpiringCollectableTypeDecorator extends CollectableTypeDecorator {

    GregorianCalendar expiryDate;

    /**
     * @param decoratedType the CollectableTypeComponent to decorate
     */
    public ExpiringCollectableTypeDecorator(CollectableTypeComponent decoratedType) {
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
     * @return the CollectableSet of the decoratedType
     */
    @Override
    public CollectableSet getCollectableSet() {
        return decoratedType.getCollectableSet();
    }

    /**
     * Sets the decoratedType's set
     * @param set the {@link CollectableSet} the decoratedType is a part of
     */
    @Override
    public void setCollectableSet(CollectableSet set) {
        decoratedType.setCollectableSet(set);
    }

    /**
     * @return true or false based on if the decoratedType is trackable or not
     */
    @Override
    public boolean getTrackable() {
        return decoratedType.getTrackable();
    }

    /**
     * @return the expiryDate
     */
    @Override
    public GregorianCalendar getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiryDate
     * @param date the calendar date on which the collectableType expires
     */
    @Override
    public void setExpiryDate(GregorianCalendar date) {
        expiryDate=date;
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
