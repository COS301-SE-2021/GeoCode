package tech.geocodeapp.geocode.user;

import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

/** @deprecated use MockCurrentUserDetails */
@Deprecated
public class MockSecurity {

    /* An Authentication object that will hold the current user status. It is reset every time setup() is called */
    private static Authentication authentication = null;

    /* Set up the security context with a blank slate */
    public static void setup() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        authentication = Mockito.mock(Authentication.class);

        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);
    }

    /* Define the user ID that will be returned when a test tries to fetch the current user */
    public static void setCurrentUserID(UUID id) {
        Mockito.when(authentication.getName()).thenReturn(id.toString());
    }
}