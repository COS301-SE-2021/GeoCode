package tech.geocodeapp.geocode.general.security.wrapper;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("CurrentUserDetails")
public class CurrentUserDetailsImpl implements CurrentUserDetails {

    @Override
    public UUID getID() {
        var uuid = SecurityContextHolder.getContext().getAuthentication().getName();
        return UUID.fromString(uuid);
    }

    @Override
    public String getUsername() {
        var principal = ( KeycloakPrincipal ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getKeycloakSecurityContext().getToken().getPreferredUsername();
    }

    @Override
    public boolean isAdmin() {
        var account = ( SimpleKeycloakAccount ) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return account.getRoles().contains( "Admin" );
    }
}
