package tech.geocodeapp.geocode.Collectable.Request;

import tech.geocodeapp.geocode.Collectable.Model.Rarity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class CreateCollectableRequest {
    private final String jwtToken;

    public CreateCollectableRequest(String jwtToken){
        this.jwtToken = jwtToken;

    }

    public String getJwtToken(){
        return this.jwtToken;
    }

}
