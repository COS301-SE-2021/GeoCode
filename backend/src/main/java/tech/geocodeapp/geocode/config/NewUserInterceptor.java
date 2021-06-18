package tech.geocodeapp.geocode.config;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import tech.geocodeapp.geocode.User.Model.User;
import tech.geocodeapp.geocode.User.Service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.UUID;

@Component
public class NewUserInterceptor extends GenericFilterBean {

    private final UserService userService;

    public NewUserInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        KeycloakSecurityContext ctx = (KeycloakSecurityContext) servletRequest.getAttribute(KeycloakSecurityContext.class.getName());
        if (ctx != null) {
            UUID uuid = UUID.fromString(ctx.getToken().getSubject());
            String username = ctx.getToken().getPreferredUsername();
            User existingUser = userService.getUserById(uuid);
            if (existingUser == null) {
                userService.registerNewUser(uuid, username);
            }
        }
    }
}
