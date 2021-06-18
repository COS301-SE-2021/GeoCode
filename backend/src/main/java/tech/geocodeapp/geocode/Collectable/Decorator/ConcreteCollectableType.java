package tech.geocodeapp.geocode.Collectable.Decorator;

import io.swagger.model.CollectableSet;
import io.swagger.model.Rarity;

import java.util.GregorianCalendar;
import java.util.UUID;

public class ConcreteCollectableType implements CollectableTypeComponent {

    private String name;
    private Rarity rarity;
    private UUID id;
    private CollectableSet set;

    public ConcreteCollectableType() {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public CollectableSet getCollectableSet() {
        return set;
    }

    @Override
    public void setCollectableSet(CollectableSet set) {
        this.set = set;
    }


    /**
     * @return false as trackable isn't contained in the concreteComponent
     */
    @Override
    public boolean getTrackable() {
        return false;
    }

    /**
     * @return null because expiry date isn't contained in the concreteComponent
     */
    @Override
    public GregorianCalendar getExpiryDate() {
        return null;
    }

    /**
     * Do nothing because date is not contained in the concreteComponent
     * @param date unused
     */
    @Override
    public void setExpiryDate(GregorianCalendar date) {

    }

    /**
     * @return null because area is not contained in the concreteComponent
     */
    @Override
    public String getArea() {
        return null;
    }

    /**
     * Do nothing because area is not contained in the concreteComponent
     * @param area unused
     */
    @Override
    public void setArea(String area) {

    }

}
