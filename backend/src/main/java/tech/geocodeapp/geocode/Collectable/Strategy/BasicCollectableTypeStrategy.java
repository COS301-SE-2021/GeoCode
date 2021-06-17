package tech.geocodeapp.geocode.Collectable.Strategy;

import tech.geocodeapp.geocode.Collectable.Factory.AbstractCollectableTypeFactory;
import tech.geocodeapp.geocode.Collectable.Factory.BasicCollectableTypeFactory;

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
