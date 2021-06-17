package tech.geocodeapp.geocode.Collectable.Strategy;


import tech.geocodeapp.geocode.Collectable.Factory.AbstractCollectableTypeFactory;

public interface CollectableTypeStrategy {
    /**
     * Abstract method to get the correct CollectableTypeFactory to use
     * @return The created factory
     */
    AbstractCollectableTypeFactory getCollectableTypeFactory();
}
