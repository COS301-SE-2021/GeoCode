package tech.geocodeapp.geocode.notifications.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import tech.geocodeapp.geocode.notifications.service.NotificationsServiceImpl;


/**
 * This class implements the functionality of the NotificationsAPI interface.
 */
@CrossOrigin("*")
@RestController
public class NotificationsController {

    @Autowired
    private NotificationsServiceImpl notificationsService;

}
