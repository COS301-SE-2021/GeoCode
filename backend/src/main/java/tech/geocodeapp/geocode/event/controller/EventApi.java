package tech.geocodeapp.geocode.event.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@CrossOrigin(origins = "${web_referrer}", maxAge = 3600)
public interface EventApi {


}