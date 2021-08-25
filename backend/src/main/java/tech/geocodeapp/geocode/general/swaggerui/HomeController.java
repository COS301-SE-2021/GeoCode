package tech.geocodeapp.geocode.general.swaggerui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Home redirection to swagger api documentation 
 */
@Controller
public class HomeController {

    @RequestMapping( path = "/", method = { RequestMethod.GET, RequestMethod.POST } )
    public String index() {

        System.out.println( "/swagger-ui/index.html" );
        return "redirect:/swagger-ui/";
    }

}
