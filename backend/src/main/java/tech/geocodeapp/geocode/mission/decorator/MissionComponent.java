package tech.geocodeapp.geocode.mission.decorator;

import java.util.UUID;

public interface MissionComponent {

    /**
     * gets the id of a mission
     * @return the UUID of the mission
     */
    UUID getId();

    /**
     *
     * @param id the id
     */
    void setId(UUID id);
}
