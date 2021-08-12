package tech.geocodeapp.geocode.event.decorator;

import tech.geocodeapp.geocode.event.model.UserEventStatus;

import java.util.Date;
import java.util.Map;

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

    /**
     * Saves the user's event start time
     * @param status The user's current status in an event
     */
    @Override
    public void handleEventStart(UserEventStatus status) {
        /* Let other decorators manipulate the status */
        super.handleEventStart(status);

        /* Record the start time */
        status.getDetails().put("startTime", getCurrentTimestamp());
    }

    /**
     * Saves a split time on the user's event progress
     * @param stageNumber The index of the stage that was just completed
     * @param status The user's current status in an event
     */
    @Override
    public void handleStageCompletion(int stageNumber, UserEventStatus status) {
        /* Let other decorators manipulate the status */
        super.handleStageCompletion(stageNumber, status);

        /* Record the new timeSplit with the stage number */
        status.getDetails().put("timeSplit_"+(stageNumber), getCurrentTimestamp());
    }

    /* -------------- HELPER FUNCTIONS ---------------- */

    /**
     * Returns the current time as a Unix timestamp (number of seconds since 1 Jan 1970 00:00)
     * @return The timestamp as a String
     */
    private String getCurrentTimestamp() {
        Date startTime = new Date();
        long timestamp = startTime.getTime()/1000;
        return ""+timestamp;
    }
}
