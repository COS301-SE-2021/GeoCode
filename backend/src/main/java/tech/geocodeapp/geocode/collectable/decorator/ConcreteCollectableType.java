package tech.geocodeapp.geocode.collectable.decorator;

import tech.geocodeapp.geocode.collectable.model.CollectableSet;
import tech.geocodeapp.geocode.collectable.model.Rarity;
import tech.geocodeapp.geocode.mission.model.MissionType;

import java.util.GregorianCalendar;
import java.util.UUID;

public class ConcreteCollectableType implements CollectableTypeComponent {

    private String name;
    private Rarity rarity;
    private UUID id;
    private CollectableSet set;
    private String image;
    private MissionType missionType;

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
    public String getImage() {
        return image;
    }

    @Override
    public void setImage(String image) {
        this.image=image;
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

    /**
     * the getter for the missionType variable
     */
    @Override
    public MissionType getMissionType() {
        return missionType;
    }

    /**
     * Setter for the missionType
     * @param missionType the mission type to set
     */
    @Override
    public void setMissionType(MissionType missionType) {
        this.missionType = missionType;
    }

}
