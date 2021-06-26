package tech.geocodeapp.geocode.collectable.strategy;

import tech.geocodeapp.geocode.collectable.factory.*;

public class BasicCollectableTypeStrategy implements CollectableTypeStrategy {

    /**
     * Creates and returns the corresponding CollectableTypeFactory Class
     * @return A new {@link BasicCollectableTypeFactory} using an {@link AbstractCollectableTypeFactory}
     */
    @Override
    public AbstractCollectableTypeFactory getCollectableTypeFactory() {
        return new BasicCollectableTypeFactory();
    }
}
