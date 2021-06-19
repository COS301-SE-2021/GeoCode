package tech.geocodeapp.geocode.Collectable.Factory;

import tech.geocodeapp.geocode.Collectable.Model.Rarity;
import tech.geocodeapp.geocode.Collectable.Decorator.CollectableTypeComponent;
import tech.geocodeapp.geocode.Collectable.Decorator.ConcreteCollectableType;

import java.util.UUID;

public class BasicCollectableTypeFactory extends AbstractCollectableTypeFactory {

    /**
     * Creates the a new {@link ConcreteCollectableType} and sets its variables to the values obtained from parsing properties
     * @param property A String containing the values of name, rarity and id in that order separated by a #
     * @param collectableTypeComponent Null as it is created here and decorated by the other factories if required
     * @return the created {@link ConcreteCollectableType} returned as a {@link CollectableTypeComponent} interface object
     */
    @Override
   public CollectableTypeComponent decorateCollectableType(String property, CollectableTypeComponent collectableTypeComponent) {
        //split into separate values for variables
        String[] split = property.split("#");

        String name = split[0];
        Rarity rarity = Rarity.valueOf(split[1]);
        UUID id = UUID.fromString(split[2]);

        CollectableTypeComponent toReturn = new ConcreteCollectableType();
        toReturn.setName(name);
        toReturn.setRarity(rarity);
        toReturn.setId(id);

        return toReturn;
   }
}
