package tech.geocodeapp.geocode.Collectable.Strategy;

import tech.geocodeapp.geocode.Collectable.Factory.AbstractCollectableTypeFactory;
import tech.geocodeapp.geocode.Collectable.Factory.TrackableCollectableTypeFactory;

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
