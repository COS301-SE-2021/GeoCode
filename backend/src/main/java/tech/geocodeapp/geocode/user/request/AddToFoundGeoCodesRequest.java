package tech.geocodeapp.geocode.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.user.model.User;

public class AddToFoundGeoCodesRequest {
    @JsonProperty("userID")
    private User user;

    @JsonProperty("geoCodeID")
    private GeoCode geocode;

    public AddToFoundGeoCodesRequest(User user, GeoCode geocode){
        this.user = user;
        this.geocode = geocode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GeoCode getGeocode() {
        return geocode;
    }

    public void setGeocode(GeoCode geocode) {
        this.geocode = geocode;
    }
}