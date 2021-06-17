package tech.geocodeapp.geocode.Collectable.Manager;

import io.swagger.model.CollectableType;
import tech.geocodeapp.geocode.Collectable.Context.CollectableTypeContext;
import tech.geocodeapp.geocode.Collectable.Decorator.CollectableTypeComponent;
import tech.geocodeapp.geocode.Collectable.Factory.AbstractCollectableTypeFactory;
import tech.geocodeapp.geocode.Collectable.Strategy.BasicCollectableTypeStrategy;

public class CollectableTypeManager {


    public CollectableTypeComponent buildCollectableType(CollectableType type){
        CollectableTypeComponent builtType;
        CollectableTypeContext context = new CollectableTypeContext(new BasicCollectableTypeStrategy());
        AbstractCollectableTypeFactory factory = context.executeStrategy();

        //create a String to store name, rarity and id in that order separated by a #
        String property;
        property = type.getName();
        property += "#";
        property += type.getRarity().toString();
        property += "#";
        property += type.getId().toString();

        //use property String to build a ConcreteCollectableType using the BasicCollectableTypeFactory
        builtType = factory.decorateCollectableType(property, null);
        builtType.setCollectableSet(type.getSet());

        //check if no additional properties exist
        if(type.getProperties().isEmpty()) {
            return builtType;
        }

        //check if expiring property exists and decorate if it does
        if(type.getProperties().containsKey("expiring")) {
            context.Switch("expiring");
            factory = context.executeStrategy();
            property = type.getProperties().get("expiring");
            builtType = factory.decorateCollectableType(property, builtType);
        }

        //check if geofenced property exists and decorate if it does
        if(type.getProperties().containsKey("geofenced")) {
            context.Switch("geofenced");
            factory = context.executeStrategy();
            property = type.getProperties().get("geofenced");
            builtType = factory.decorateCollectableType(property, builtType);
        }

        //check if trackable property exists and decorate if it does
        if(type.getProperties().containsKey("trackable")) {
            context.Switch("trackable");
            factory = context.executeStrategy();
            property = type.getProperties().get("trackable");
            builtType = factory.decorateCollectableType(property, builtType);
        }

        return builtType;
    }
}
