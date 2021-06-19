package tech.geocodeapp.geocode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.EntityListeners;

@SpringBootApplication
public class GeocodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeocodeApplication.class, args);
	}

}
