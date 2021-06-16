package tech.geocodeapp.geocode.Collectable.Factory;

import tech.geocodeapp.geocode.Collectable.Decorator.CollectableTypeComponent;
import tech.geocodeapp.geocode.Collectable.Decorator.TrackableCollectableTypeDecorator;

public class TrackableCollectableTypeFactory extends AbstractCollectableTypeFactory {

    /**
     * Decorates a provided {@link CollectableTypeComponent}
     * @param property Unused as trackable will always have the value of trackable as true
     * @param collectableTypeComponent The {@link CollectableTypeComponent} to decorate
     * @return The decorated {@link CollectableTypeComponent}
     */
    @Override
    CollectableTypeComponent decorateCollectableType(String property, CollectableTypeComponent collectableTypeComponent) {
        return new TrackableCollectableTypeDecorator(collectableTypeComponent);
    }
}
