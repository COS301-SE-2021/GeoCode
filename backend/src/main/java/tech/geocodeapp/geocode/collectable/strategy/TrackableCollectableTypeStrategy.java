package tech.geocodeapp.geocode.collectable.strategy;

import tech.geocodeapp.geocode.collectable.factory.*;

public class TrackableCollectableTypeStrategy implements CollectableTypeStrategy {

    /**
     * Creates and returns the corresponding CollectableTypeFactory Class
     * @return A new {@link TrackableCollectableTypeFactory} using an {@link AbstractCollectableTypeFactory}
     */
    @Override
    public AbstractCollectableTypeFactory getCollectableTypeFactory() {
        return new TrackableCollectableTypeFactory();
    }
}
