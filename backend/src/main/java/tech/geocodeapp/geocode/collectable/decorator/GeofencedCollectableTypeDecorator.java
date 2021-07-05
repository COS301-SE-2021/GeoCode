package tech.geocodeapp.geocode.collectable.decorator;

public class GeofencedCollectableTypeDecorator extends CollectableTypeDecorator {

    String area;

    /**
     * @param decoratedType the CollectableTypeComponent to decorate
     */
    public GeofencedCollectableTypeDecorator(CollectableTypeComponent decoratedType) {
        super(decoratedType);
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
