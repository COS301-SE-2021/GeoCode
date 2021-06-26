package tech.geocodeapp.geocode.notifications.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tech.geocodeapp.geocode.notifications.repository.NotificationsRepository;


/**
 * This class implements the NotificationsService interface
 */
@Service
public class NotificationsServiceImpl implements NotificationsService {

    @Autowired
    private NotificationsRepository notificationsRepo;

    public NotificationsServiceImpl() {

    }


}
