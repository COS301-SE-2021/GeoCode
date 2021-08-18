package tech.geocodeapp.geocode.event.decorator;

import tech.geocodeapp.geocode.event.model.UserEventStatus;
import tech.geocodeapp.geocode.geocode.model.GeoCode;

import java.util.Date;

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

        /* Record the start time as the first timeSplit */
        status.getDetails().put("timeSplit_0", ""+getCurrentTimestamp());
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
        status.getDetails().put("timeSplit_"+stageNumber, ""+getCurrentTimestamp());
    }

    /**
     * Function to calculate the number of points a user should receive for finding the provided geocode.
     *
     * @param foundGeocode The geocode that was just found
     * @param stageNumber The index of the stage that was just completed
     * @param status The user's current status in an event
     *
     * @return The number of points earned, adjusted to account for time taken
     */
    @Override
    public int calculatePoints(GeoCode foundGeocode, int stageNumber, UserEventStatus status) {
        int basePointsAmount = super.calculatePoints(foundGeocode, stageNumber, status);

        int newPointsAmount = basePointsAmount;

        /* Get the time the user finished the previous and current stages */
        String startTimeStr = status.getDetails().get("timeSplit_0");
        if (startTimeStr != null) {
            try {
                long startTime = Long.parseLong(startTimeStr);
                long currentTime = getCurrentTimestamp();

                long timeTakenSeconds = currentTime - startTime;
                double timeTakenMinutes = timeTakenSeconds / 60.0;

                /* Calculate bonus points from the base points and the number of minutes the user has remaining */
                double timeRemainingMinutes = timeLimit - timeTakenMinutes;
                double percentageTimeRemaining = timeRemainingMinutes / timeLimit;

                /* If the user is over the time limit, the pointsBonus will be negative */
                int pointsBonus = (int) Math.round(basePointsAmount * percentageTimeRemaining);
                newPointsAmount = basePointsAmount + pointsBonus;

                if (newPointsAmount >= 0) {
                    /* Only return the newPointsAmount if it is zero or positive. */
                    return newPointsAmount;
                }
                /* If the newPointsAmount is negative, return 0 below */

            } catch (NumberFormatException e) {}
        }
        return 0;
    }



    /* -------------- HELPER FUNCTIONS ---------------- */

    /**
     * Returns the current time as a Unix timestamp (number of seconds since 1 Jan 1970 00:00)
     * @return The timestamp
     */
    private long getCurrentTimestamp() {
        Date startTime = new Date();
        return startTime.getTime()/1000;
    }
}
