package tech.geocodeapp.geocode.user;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

/** @deprecated use CurrentUserDetails.injectUserDetails */
@Deprecated
public class MockSecurity {

    /* An Authentication object that will hold the current user status. It is reset every time setup() is called */
    private static Authentication authentication = null;

    private static KeycloakPrincipal principal = null;

    private static SimpleKeycloakAccount account = null;

    /* Set up the security context with a blank slate */
    public static void setup() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        authentication = Mockito.mock(Authentication.class);

        principal = Mockito.mock(KeycloakPrincipal.class);
        account = Mockito.mock(SimpleKeycloakAccount.class);

        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(securityContext.getAuthentication().getPrincipal()).thenReturn(principal);
        Mockito.when(securityContext.getAuthentication().getDetails()).thenReturn(account);

        SecurityContextHolder.setContext(securityContext);
    }

    /* Define the user ID that will be returned when a test tries to fetch the current user */
    public static void setCurrentUserID(UUID id) {
        Mockito.when(authentication.getName()).thenReturn(id.toString());
    }

    public static void setCurrentUsername(String username) {
        Mockito.when(principal.getKeycloakSecurityContext().getToken().getPreferredUsername()).thenReturn(username);
    }

    public static void setCurrentUserIsAdmin(Boolean isAdmin) {
        Mockito.when(account.getRoles().contains("Admin")).thenReturn(isAdmin);
    }
}