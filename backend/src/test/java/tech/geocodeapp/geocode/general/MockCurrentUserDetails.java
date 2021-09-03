package tech.geocodeapp.geocode.general;

import tech.geocodeapp.geocode.general.security.wrapper.CurrentUserDetails;

import java.util.UUID;

public class MockCurrentUserDetails implements CurrentUserDetails {

    private UUID userID;
    private String username;
    private boolean admin;

    @Override
    public UUID getID() {
        return userID;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAdmin() {
        return admin;
    }

    public void setID(UUID userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
