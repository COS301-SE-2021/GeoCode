package tech.geocodeapp.geocode.general.security.wrapper;

import java.util.UUID;

public interface CurrentUserDetails {

    UUID getID();

    String getUsername();

    boolean isAdmin();
}
