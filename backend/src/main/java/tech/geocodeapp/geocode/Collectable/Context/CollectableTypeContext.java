package tech.geocodeapp.geocode.Collectable.Context;

import tech.geocodeapp.geocode.Collectable.Factory.AbstractCollectableTypeFactory;
import tech.geocodeapp.geocode.Collectable.Strategy.*;

public class CollectableTypeContext {
    private CollectableTypeStrategy strategy;


    public CollectableTypeContext(CollectableTypeStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Change the Strategy being used based on a provided property
     * @param property A String containing the name of the property
     */
    public void Switch(String property) {
        //if none use BasicCollectableTypeStrategy
        if(property.equalsIgnoreCase("none")){
            this.strategy = new BasicCollectableTypeStrategy();
        }else if(property.equalsIgnoreCase("trackable")){
            this.strategy = new TrackableCollectableTypeStrategy();
        }else if(property.equalsIgnoreCase("geofenced")){
            this.strategy = new GeofencedCollectableTypeStrategy();
        }else if(property.equalsIgnoreCase("expiring")){
            this.strategy = new ExpiringCollectableTypeStrategy();
        }
    }


    public AbstractCollectableTypeFactory executeStrategy() {
        return strategy.getCollectableTypeFactory();
    }
}
