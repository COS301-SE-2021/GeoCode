package tech.geocodeapp.geocode.general.security;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class CurrentUserDetails {

    private static UUID injectedID = null;
    private static String injectedUsername = null;
    private static Boolean injectedIsAdmin = null;

    public static UUID getID() {
        if (injectedID == null) {
            var uuid = SecurityContextHolder.getContext().getAuthentication().getName();
            return UUID.fromString(uuid);

        } else {
            return injectedID;
        }
    }

    public static String getUsername() {
        if (injectedUsername == null) {
            var principal = ( KeycloakPrincipal ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return principal.getKeycloakSecurityContext().getToken().getPreferredUsername();

        } else {
            return injectedUsername;
        }
    }

    public static boolean isAdmin() {
        if (injectedIsAdmin == null) {
            var account = ( SimpleKeycloakAccount ) SecurityContextHolder.getContext().getAuthentication().getDetails();
            return account.getRoles().contains( "Admin" );

        } else {
            return injectedIsAdmin;
        }
    }

    public static void injectUserDetails(UUID id, String username, Boolean isAdmin) {
        injectedID = id;
        injectedUsername = username;
        injectedIsAdmin = isAdmin;
    }
}
