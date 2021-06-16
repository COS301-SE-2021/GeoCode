package tech.geocodeapp.geocode.Collectable.Factory;

import tech.geocodeapp.geocode.Collectable.Decorator.CollectableTypeComponent;
import tech.geocodeapp.geocode.Collectable.Decorator.ExpiringCollectableTypeDecorator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ExpiringCollectableTypeFactory extends AbstractCollectableTypeFactory {

    /**
     * Parses the property String to decorate a CollectableTypeComponent
     * @param property - A String representation of the date the CollectableType is set to expire on
     * @param collectableTypeComponent The collectableTypeComponent to decorate
     * @return the decorated ExpiringCollectableTypeDecorator as a CollectableTypeComponent object
     */
    @Override
    CollectableTypeComponent decorateCollectableType(String property, CollectableTypeComponent collectableTypeComponent) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        GregorianCalendar expiryDate = new GregorianCalendar();

        try {
            Date date = format.parse(property);
            expiryDate.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        CollectableTypeComponent toReturn = new ExpiringCollectableTypeDecorator(collectableTypeComponent);
        toReturn.setExpiryDate(expiryDate);
        return toReturn;
    }
}
