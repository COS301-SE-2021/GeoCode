package tech.geocodeapp.geocode.collectable.strategy;

import tech.geocodeapp.geocode.collectable.factory.*;

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
