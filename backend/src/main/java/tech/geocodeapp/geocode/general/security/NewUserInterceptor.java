package tech.geocodeapp.geocode.general.security;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.user.request.GetUserByIdRequest;
import tech.geocodeapp.geocode.user.request.RegisterNewUserRequest;
import tech.geocodeapp.geocode.user.response.GetUserByIdResponse;
import tech.geocodeapp.geocode.user.service.UserService;

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
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws ServletException, IOException {
//        KeycloakSecurityContext ctx = (KeycloakSecurityContext) servletRequest.getAttribute( KeycloakSecurityContext.class.getName() );
//        if ( ctx != null ) {
//            UUID uuid = UUID.fromString(ctx.getToken().getSubject());
//            String username = ctx.getToken().getPreferredUsername();
//
//            try {
//                RegisterNewUserRequest registerNewUserRequest = new RegisterNewUserRequest(username);
//                userService.registerNewUser( registerNewUserRequest );
//            } catch ( NullRequestParameterException e ) {
//                return;
//            }
//        }
       filterChain.doFilter(servletRequest, servletResponse);
    }
}
