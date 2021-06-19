package tech.geocodeapp.geocode.Collectable.Factory;

import tech.geocodeapp.geocode.Collectable.Decorator.CollectableTypeComponent;

public abstract class AbstractCollectableTypeFactory {

    /**
     *
     * @param property the value of the property to decorate the component with or the values to use in the {@link BasicCollectableTypeFactory}
     * @param collectableTypeComponent The collectableTypeComponent to decorate or null if there is none in the case of a {@link BasicCollectableTypeFactory}
     * @return the decorated CollectableTypeComponent created
     */
    public abstract CollectableTypeComponent decorateCollectableType(String property, CollectableTypeComponent collectableTypeComponent);
}
