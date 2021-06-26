package tech.geocodeapp.geocode.collectable.factory;

import tech.geocodeapp.geocode.collectable.decorator.*;

public class GeofencedCollectableTypeFactory extends AbstractCollectableTypeFactory {

    /**
     * Decorates a provided CollectableTypeComponent using the value provided in property
     * @param property The value of the area the CollectableType is restricted to
     * @param collectableTypeComponent The collectableTypeComponent to decorate
     * @return The decorated CollectableTypeComponent
     */
    @Override
   public CollectableTypeComponent decorateCollectableType(String property, CollectableTypeComponent collectableTypeComponent) {
        CollectableTypeComponent toReturn = new GeofencedCollectableTypeDecorator(collectableTypeComponent);
        toReturn.setArea(property);

        return toReturn;
   }
}
