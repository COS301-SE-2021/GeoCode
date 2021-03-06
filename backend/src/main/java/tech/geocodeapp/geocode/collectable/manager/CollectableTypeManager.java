package tech.geocodeapp.geocode.collectable.manager;

import tech.geocodeapp.geocode.collectable.factory.*;
import tech.geocodeapp.geocode.collectable.model.CollectableType;
import tech.geocodeapp.geocode.collectable.decorator.CollectableTypeComponent;
import tech.geocodeapp.geocode.mission.model.MissionType;

import java.util.HashMap;

public class CollectableTypeManager {

    /**
     * Converts a {@link CollectableType} to a decorated {@link CollectableTypeComponent}
     * @param type The {@link CollectableType} to convert
     * @return The {@link CollectableTypeComponent} that has been created
     */
    public CollectableTypeComponent buildCollectableType(CollectableType type){
        CollectableTypeComponent builtType;
        AbstractCollectableTypeFactory factory = new BasicCollectableTypeFactory();

        //create a String to store name, rarity, id and image in that order separated by a #
        String property;
        property = type.getName();
        property += "#";
        property += type.getRarity().toString();
        property += "#";
        property += type.getId().toString();
        property += "#";
        property += type.getImage();

        //use property String to build a ConcreteCollectableType using the BasicCollectableTypeFactory
        builtType = factory.decorateCollectableType(property, null);
        builtType.setCollectableSet(type.getSet());

        //check if no additional properties exist
        if(type.getProperties()==null || type.getProperties().isEmpty()) {
            return builtType;
        }

        //check if the type has a mission associated with it
        if(type.getProperties().containsKey("missionType")) {
            builtType.setMissionType(MissionType.fromValue(type.getProperties().get("missionType")));
        }

        //check if expiring property exists and decorate if it does
        if(type.getProperties().containsKey("expiring")) {
            factory = new ExpiringCollectableTypeFactory();
            property = type.getProperties().get("expiring");
            builtType = factory.decorateCollectableType(property, builtType);
        }

        //check if geofenced property exists and decorate if it does
        if(type.getProperties().containsKey("geofenced")) {
            factory = new GeofencedCollectableTypeFactory();
            property = type.getProperties().get("geofenced");
            builtType = factory.decorateCollectableType(property, builtType);
        }

        //check if trackable property exists and decorate if it does
        if(type.getProperties().containsKey("trackable")) {
            factory = new TrackableCollectableTypeFactory();
            property = type.getProperties().get("trackable");
            builtType = factory.decorateCollectableType(property, builtType);
        }

        return builtType;
    }

    /**
     * Converts a {@link CollectableTypeComponent} to a {@link CollectableType} for use in other backend systems
     * @param type The {@link CollectableTypeComponent} to convert
     * @return the converted {@link CollectableType}
     */
    public CollectableType convertToCollectableType(CollectableTypeComponent type){
        CollectableType convertedType = new CollectableType();
        convertedType.setId(type.getId());
        convertedType.setName(type.getName());
        convertedType.setRarity(type.getRarity());
        convertedType.setImage(type.getImage());
        convertedType.setSet(type.getCollectableSet());

        //check what properties apply and add them to a hashmap
        HashMap<String, String> properties = new HashMap<>();
        if(type.getTrackable()){
            properties.put("trackable", "True");
        }
        if(type.getExpiryDate()!=null){
            properties.put("expiring", type.getExpiryDate().toString());
        }
        if(type.getArea()!=null){
            properties.put("geofenced", type.getArea());
        }
        if(type.getMissionType()!=null){
            properties.put("missionType", type.getMissionType().toString());
        }
        if(!properties.isEmpty()){
            convertedType.setProperties(properties);
        }
        return convertedType;
    }
}
