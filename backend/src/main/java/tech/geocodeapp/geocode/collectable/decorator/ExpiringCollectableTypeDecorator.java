package tech.geocodeapp.geocode.collectable.decorator;

import java.util.GregorianCalendar;

public class ExpiringCollectableTypeDecorator extends CollectableTypeDecorator {

    GregorianCalendar expiryDate;

    /**
     * @param decoratedType the CollectableTypeComponent to decorate
     */
    public ExpiringCollectableTypeDecorator(CollectableTypeComponent decoratedType) {
        super(decoratedType);
    }

    /**
     * @return the expiryDate
     */
    @Override
    public GregorianCalendar getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiryDate
     * @param date the calendar date on which the collectableType expires
     */
    @Override
    public void setExpiryDate(GregorianCalendar date) {
        expiryDate=date;
    }

}
