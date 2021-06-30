package tech.geocodeapp.geocode.collectable.decorator;

public class TrackableCollectableTypeDecorator extends CollectableTypeDecorator {

    /**
     * @param decoratedType the CollectableTypeComponent to decorate
     */
    public TrackableCollectableTypeDecorator(CollectableTypeComponent decoratedType) {
        super(decoratedType);
    }

    /**
     * @return true as this class adds trackable functionality
     */
    @Override
    public boolean getTrackable() {
        return true;
    }

}
