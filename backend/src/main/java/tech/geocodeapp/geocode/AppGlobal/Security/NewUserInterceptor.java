package tech.geocodeapp.geocode.appglobal.security;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import tech.geocodeapp.geocode.User.Model.User;
import tech.geocodeapp.geocode.User.Service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class NewUserInterceptor extends GenericFilterBean {

    private final UserService userService;

    public NewUserInterceptor(UserService userService) {
        this.userService = userService;
    }

    /*
     * Intercepts all incoming requests and registers new users who are not present in the GeoCode database.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        KeycloakSecurityContext ctx = (KeycloakSecurityContext) servletRequest.getAttribute(KeycloakSecurityContext.class.getName());
        if (ctx != null) {
            UUID uuid = UUID.fromString(ctx.getToken().getSubject());
            String username = ctx.getToken().getPreferredUsername();
            User existingUser = userService.getUserById(uuid);
            if (existingUser == null) {
                userService.registerNewUser(uuid, username);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
