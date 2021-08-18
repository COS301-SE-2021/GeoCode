package tech.geocodeapp.geocode.user.request;

import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.user.model.User;

public class AddToMyMissionsRequest {
    private User user;

    private Mission mission;

    public AddToMyMissionsRequest(User user, Mission mission) {
        this.user = user;
        this.mission = mission;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
}
