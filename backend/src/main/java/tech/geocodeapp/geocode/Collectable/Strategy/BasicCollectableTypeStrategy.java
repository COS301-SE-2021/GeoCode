package tech.geocodeapp.geocode.Collectable.Strategy;

import tech.geocodeapp.geocode.Collectable.Factory.AbstractCollectableTypeFactory;
import tech.geocodeapp.geocode.Collectable.Factory.BasicCollectableTypeFactory;

public class BasicCollectableTypeStrategy implements CollectableTypeStrategy {

    /**
     * Creates and returns the corresponding CollectableTypeFactory Class
     * @return A new BasicCollectableTypeFactory to use
     */
    @Override
    public AbstractCollectableTypeFactory getCollectableTypeFactory() {
        return new BasicCollectableTypeFactory();
    }
}
