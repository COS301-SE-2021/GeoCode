package tech.geocodeapp.geocode.event.decorator;

public class TimeTrialEventDecorator extends EventDecorator {

    private Integer timeLimit;
    /**
     * @param decoratedType the EventComponent to decorate
     */
    public TimeTrialEventDecorator(EventComponent decoratedType) { super(decoratedType); }

    /**
     * @return the event's time limit
     */
    @Override
    public Integer getTimeLimit() {
        return timeLimit;
    }

    /**
     * Sets the event's time limit
     * @param limit the event's time limit
     */
    @Override
    public void setTimeLimit(Integer limit) {
        this.timeLimit = limit;
    }

}
