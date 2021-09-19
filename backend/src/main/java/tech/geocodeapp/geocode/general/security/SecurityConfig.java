package tech.geocodeapp.geocode.general.security;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    private final String allowedOrigins;
    private final RequestMatcher publicEndpoints;

    public SecurityConfig( @Value("${allowed-origins}") String allowedOrigins ) {
        this.allowedOrigins = allowedOrigins;

        publicEndpoints = new OrRequestMatcher(
                new AntPathRequestMatcher( "/Image/getImage/*" )
        );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {

        super.configure( http );
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .requestMatchers( publicEndpoints )
                    .permitAll()
                .anyRequest()
                    .hasRole( "User" );
    }


    @Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth ) {

        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper( new SimpleAuthorityMapper() );
        auth.authenticationProvider( keycloakAuthenticationProvider );
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {

        return new RegisterSessionAuthenticationStrategy( new SessionRegistryImpl() );
    }

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver() {

        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings( CorsRegistry registry ) {
                registry.addMapping( "/**" )
                    .allowedOrigins( allowedOrigins.split( "," ) )
                    .maxAge( 3600 );
            }
        };
    }

}