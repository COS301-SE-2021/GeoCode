package tech.geocodeapp.geocode.Collectable.Decorator;

import io.swagger.model.CollectableSet;
import io.swagger.model.Rarity;

import java.util.GregorianCalendar;
import java.util.UUID;

public class GeofencedCollectableTypeDecorator extends CollectableTypeDecorator {

    String area;

    /**
     * @param decoratedType the CollectableTypeComponent to decorate
     */
    public GeofencedCollectableTypeDecorator(CollectableTypeComponent decoratedType) {
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
        return super.getTrackable();
    }

    /**
     * @return the expiryDate of the decoratedType
     */
    @Override
    public GregorianCalendar getExpiryDate() {
        return super.getExpiryDate();
    }

    /**
     * Sets the decoratedType's expiryDate
     * @param date the calendar date on which the collectableType expires
     */
    @Override
    public void setExpiryDate(GregorianCalendar date) {
        super.setExpiryDate(date);
    }

    /**
     * @return the area the geocode must stay within
     */
    @Override
    public String getArea() {
        return area;
    }

    /**
     * Sets area
     * @param area the area a collectable type is geographically bound to
     */
    @Override
    public void setArea(String area) {
        this.area=area;
    }
}
