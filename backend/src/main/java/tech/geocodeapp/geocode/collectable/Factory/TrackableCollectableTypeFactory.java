package tech.geocodeapp.geocode.collectable.factory;

import tech.geocodeapp.geocode.collectable.decorator.*;

public class TrackableCollectableTypeFactory extends AbstractCollectableTypeFactory {

    /**
     * Decorates a provided {@link CollectableTypeComponent}
     * @param property Unused as trackable will always have the value of trackable as true
     * @param collectableTypeComponent The {@link CollectableTypeComponent} to decorate
     * @return The decorated {@link CollectableTypeComponent}
     */
    @Override
    public CollectableTypeComponent decorateCollectableType(String property, CollectableTypeComponent collectableTypeComponent) {
        return new TrackableCollectableTypeDecorator(collectableTypeComponent);
    }
}
