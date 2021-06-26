package tech.geocodeapp.geocode.collectable.strategy;

import tech.geocodeapp.geocode.collectable.factory.*;

public interface CollectableTypeStrategy {
    /**
     * Abstract method to get the correct CollectableTypeFactory to use
     * @return The created factory
     */
    AbstractCollectableTypeFactory getCollectableTypeFactory();
}
