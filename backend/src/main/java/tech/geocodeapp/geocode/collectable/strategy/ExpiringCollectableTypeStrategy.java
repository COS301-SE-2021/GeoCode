package tech.geocodeapp.geocode.collectable.strategy;

import tech.geocodeapp.geocode.collectable.factory.*;

public class ExpiringCollectableTypeStrategy implements CollectableTypeStrategy {

    /**
     * Creates and returns the corresponding CollectableTypeFactory Class
     * @return A new {@link ExpiringCollectableTypeFactory} using an {@link AbstractCollectableTypeFactory}
     */
    @Override
    public AbstractCollectableTypeFactory getCollectableTypeFactory() {
        return new ExpiringCollectableTypeFactory();
    }
}
