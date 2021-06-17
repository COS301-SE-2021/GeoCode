package tech.geocodeapp.geocode.Collectable.Strategy;

import tech.geocodeapp.geocode.Collectable.Factory.AbstractCollectableTypeFactory;
import tech.geocodeapp.geocode.Collectable.Factory.GeofencedCollectableTypeFactory;

public class GeofencedCollectableTypeStrategy implements CollectableTypeStrategy {

    /**
     * Creates and returns the corresponding CollectableTypeFactory Class
     * @return A new {@link GeofencedCollectableTypeFactory} using an {@link AbstractCollectableTypeFactory}
     */
    @Override
    public AbstractCollectableTypeFactory getCollectableTypeFactory() {
        return new GeofencedCollectableTypeFactory();
    }
}
